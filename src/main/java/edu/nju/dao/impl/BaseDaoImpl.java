package edu.nju.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.RollbackException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.nju.dao.BaseDao;

@Repository
public class BaseDaoImpl implements BaseDao {
	@Autowired
	protected SessionFactory sessionFactory;

	@PersistenceUnit
	protected EntityManagerFactory emFactory;
	private final ThreadLocal<EntityManager> emThreadLocal = new ThreadLocal<>();

	@Override
	public EntityManager getEntityManager() {
		EntityManager em = this.emThreadLocal.get();
		if (em == null || !em.isOpen()) {
			em = this.emFactory.createEntityManager();
			this.emThreadLocal.set(em);
		}
		return em;
	}

	@Override
	public EntityManager getNewEntityManager() {
		return this.emFactory.createEntityManager();
	}

	private void transcaction(Consumer<EntityManager> action) {
		EntityManager em = this.getEntityManager();
		try {
			em.getTransaction().begin();
			action.accept(em);
			em.getTransaction().commit();
		} catch (RollbackException e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}
	}

	/** * gerCurrentSession 会自动关闭session，使用的是当前的session事务 * * @return */
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/** * openSession 需要手动关闭session 意思是打开一个新的session * * @return */
	public Session getNewSession() {
		return sessionFactory.openSession();
	}

	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

	/** * 根据 id 查询信息 * * @param id * @return */
	public <T> T load(Class<T> c, int id) {
		return this.getEntityManager().find(c, id);
	}

	/** * 获取所有信息 * * @param c * * @return */

	public <T> List<T> getAllList(Class<T> c) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(c);

		Root<T> root = query.from(c);
		query.select(root).orderBy(builder.desc(root.get("id")));

		return this.getEntityManager().createQuery(query).getResultList();
	}

	/** * 获取总数量 * * @param c * @return */

	public long getTotalCount(Class<?> c) {
		CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		query.select(builder.count(query.from(c)));

		return this.getEntityManager().createQuery(query).getSingleResult();
	}

	/** * 保存 * * @param bean * */
	public void save(Object bean) {
		this.save(bean, null);
	}

	public void save(Object bean, Object key) {
		this.transcaction(em -> {
			if (key == null ||
					em.find(bean.getClass(), key) == null) {
				em.persist(bean);
			}
		});
	}

	/** * 更新 * * @param bean * */
	public void update(Object bean) {
		this.transcaction(em -> em.merge(bean));
	}

	/** * 删除 * * @param bean * */
	public void delete(Object bean) {
		this.transcaction(em -> em.remove(bean));
	}

	/** * 根据ID删除 * * @param c 类 * * @param id ID * */
	public void delete(Class<?> c, Serializable id) {
		this.transcaction(em -> em.remove(em.find(c, id)));
	}

	/** * 批量删除 * * @param c 类 * * @param ids ID 集合 * */
	public void delete(Class<?> c, Serializable... ids) {
		this.transcaction(em -> {
			for (Serializable id : ids) {
				em.remove(em.find(c, id));
			}
		});
	}

	// 根据HQL语句进行查询
	@SuppressWarnings("unchecked")
	public <T> List<T> find(String queryString) {
		try (Session session = getNewSession()) {
			Query<T> query=session.createQuery(queryString);
			return query.getResultList();
		}
	}

	public <T> List<T> find(Class<T> type, Map<String, Object> columnValuePairs) {
		EntityManager em = this.getEntityManager();
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(type);
		Root<T> root = query.from(type);

		Predicate condition = builder.and(
				columnValuePairs.entrySet().stream()
						.map(p -> p.getValue() == null
                                ? builder.isNull(root.get(p.getKey()))
                                : builder.equal(root.get(p.getKey()), p.getValue()))
						.toArray(Predicate[]::new));

		query.select(root).where(condition);

		return em.createQuery(query).getResultList();
	}
}
