var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },
    save : function () {
        let data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        let data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        let id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        let id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

function wbGetJson(successCallBackFnc, failCallBackFnc, url){
    $.ajax({
        type: 'GET',
        url: url,
        dataType: 'json',
        contentType:'application/json; charset=utf-8'
    }).done(function(ret) {
        successCallBackFnc(ret);
    }).fail(function (error) {
        failCallBackFnc(error);
    });
}


function wbSave(successCallBackFnc, failCallBackFnc, url, param){
    $.ajax({
        type: 'POST',
        url: url,
        dataType: 'json',
        contentType:'application/json; charset=utf-8',
        data: JSON.stringify(param)
    }).done(function(ret) {
        successCallBackFnc(ret);
    }).fail(function (error) {
        failCallBackFnc(error);
    });
}

function wbUpdate(successCallBackFnc, failCallBackFnc, url, param){
    $.ajax({
        type: 'PUT',
        url: url,
        dataType: 'json',
        contentType:'application/json; charset=utf-8',
        data: JSON.stringify(param)
    }).done(function(ret) {
        successCallBackFnc(ret);
    }).fail(function (error) {
        failCallBackFnc(error);
    });
}

function wbDelete(successCallBackFnc, failCallBackFnc, url){
    $.ajax({
        type: 'DELETE',
        url: url,
        dataType: 'json',
        contentType:'application/json; charset=utf-8'
    }).done(function(ret) {
        successCallBackFnc(ret);
    }).fail(function (error) {
        failCallBackFnc(error);
    });
}


main.init();
onInit();
