/**
 * 
 */
// 缩略图预览
document.querySelector('#uploaderFiles').addEventListener('click', function(e){
    var target = e.target;

    while(!target.classList.contains('weui-uploader__file') && target){
        target = target.parentNode;
    }
    if(!target) return;

    var url = target.getAttribute('style') || '';
    var id = target.getAttribute('data-id');

    if(url){
        url = url.match(/url\((.*?)\)/)[1].replace(/"/g, '');
    }
    var gallery = weui.gallery(url, {
        className: 'custom-name',
        onDelete: function(){
            weui.confirm('确定删除该图片？', function(){
                --uploadCount;
                uploadCountDom.innerHTML = uploadCount;


                for (var i = 0, len = uploadList.length; i < len; ++i) {
                    var file = uploadList[i];
                    if(file.id == id){
                        file.stop();
                        break;
                    }
                }
                target.remove();
                gallery.hide();
            });
        }
    });
});


/* 图片手动上传 */
var uploadCustomFileList = [];

// 这里是简单的调用
weui.uploader('#uploaderCustom', {
    url: 'http://localhost:8002/upload',
    auto: false,
    onQueued: function() {
        uploadCustomFileList.push(this);
    }
});

// 手动上传按钮
document.getElementById("uploaderCustomBtn").addEventListener('click', function(){
    uploadCustomFileList.forEach(function(file){
        file.upload();
    });
});

// 缩略图预览
document.querySelector('#uploaderCustomFiles').addEventListener('click', function(e){
    var target = e.target;

    while(!target.classList.contains('weui-uploader__file') && target){
        target = target.parentNode;
    }
    if(!target) return;

    var url = target.getAttribute('style') || '';
    var id = target.getAttribute('data-id');

    if(url){
        url = url.match(/url\((.*?)\)/)[1].replace(/"/g, '');
    }
    var gallery = weui.gallery(url, {
        onDelete: function(){
            weui.confirm('确定删除该图片？', function(){
                var index;
                for (var i = 0, len = uploadCustomFileList.length; i < len; ++i) {
                    var file = uploadCustomFileList[i];
                    if(file.id == id){
                        index = i;
                        break;
                    }
                }
                if(index !== undefined) uploadCustomFileList.splice(index, 1);

                target.remove();
                gallery.hide();
            });
        }
    });
});