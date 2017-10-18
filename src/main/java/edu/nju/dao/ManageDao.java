package edu.nju.dao;

import java.util.List;

import edu.nju.entities.Device;
import edu.nju.entities.UserInfo;

public interface ManageDao {

	List<Device> getDevices();

	List<UserInfo> getUsers();

}
