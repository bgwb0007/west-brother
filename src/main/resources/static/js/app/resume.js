var tistoryList = []

function onInit() {
    getContactList();
    appendTistoryModal(tstTitleMap);
    //tstTitleMap[id][1] -> contentDetail 값으로 티스토리 게시글의 제목에 포함되는 컨텐츠만 가져온다.

    let tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    let tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl)
    })
}

function moveBack() {
    location.href = '/';
}

function getContactList() {
    var url = '/api/v1/contact/이력서';
    wbGetJson(function (res) {
        var html = '';
        $.each(res, function (idx, item) {
            html += '<div class="contactInfoDiv">';
            html += '    <span class="logo"><img src="' + item.logoHtml + '" alt="인스타로고" width="35"></span>';
            html += '    <span class="siteId">' + item.siteId + '</span>';
            html += '</div>';
        })
        $('#contactInfoAll').append(html);
    }, function (res) {

    }, url, {});
}

function appendTistoryModal(tstTitleMap) {
    $.each(tstTitleMap, function (id, value) {
        let title = value[0]
        let modalId = 'tstModal-' + id;
        let modalLabel = modalId + '-Label'
        let html = '';
        html += '<div class="modal fade" id="' + modalId + '" tabindex="-1" aria-labelledby="' + modalLabel + '" aria-hidden="true">';
        html += '    <div class="modal-dialog modal-xl modal-dialog-scrollable">';
        html += '        <div class="modal-content">';
        html += '            <div class="modal-header">';
        html += '                <h5 class="modal-title" id="' + modalLabel + '">' + title + '</h5>';
        html += '                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>';
        html += '            </div>';
        html += '            <div class="list-group modal-body">';
        html += '            </div>';
        html += '            <div class="modal-footer">';
        html += '                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>';
        html += '            </div>';
        html += '        </div>';
        html += '    </div>';
        html += '</div>';
        $('body').append(html);
    });
}

function appendPostList(postId) {
    var modalId = 'tstModal-' + postId
    var appendSelectedList = function () {
        if ($('#' + modalId + ' .modal-body div')[0] != undefined) return;

        let selectedList = tistoryList.filter(function (content) {
            return content.title.indexOf(tstTitleMap[postId][1]) >= 0;
        });
        let html = '';
        $.each(selectedList, function (idx, item) {
            html += '<div id="tstPost-' + item.id + '" class="tstPost">';
            let title = item.title.split(tstTitleMap[postId][1])[1].trim();
            html += '    <button type="button" onclick="appendPostDetail(\'' + item.id + '\');" class="list-group-item list-group-item-action">' + title + '</button>';
            html += '</div>';
        });
        if (selectedList.length < 1) {
            html += '<div class="tstPost">';
            html += '    <button type="button" class="list-group-item list-group-item-action">준비중입니다.</button>';
            html += '</div>';
        }
        ;$('#' + modalId + ' .modal-body').append(html);
    }

    // TODO. 해당 리스트 가져오기 -> 모달 만들어서 append(id = modalId)
    if (tistoryList.length > 0) {
        appendSelectedList();
    } else {
        getTistoryList(function (ret) {
            console.log("##티스토리 성공##", ret);
            tistoryList = ret.tistory.item.posts;
            appendSelectedList();
        });
    }
    var myModal = new bootstrap.Modal($('#' + modalId));
    myModal.show();
}

function appendPostDetail(num) {
    let selId = '#tstPost-' + num;
    let selButton = selId + ' button';

    if ($(selButton).hasClass('active')) {
        $(selButton).removeClass('active');
        $(selButton).next().remove();
        return;
    }

    $(selButton).addClass('active');

    getTistoryDetail(num, function (ret) {
        console.log("##티스토리 성공##", ret);
        let html = '<div class="tstDetail">';
        html += ret.tistory.item.content;
        html += '</div>'
        $(selId).append(html);
        hljs.highlightAll();
    })
}

function getTistoryList(successCallBack) {
    let url = '/api/v1/tistory';
    wbGetJson(successCallBack, function (ret) {
        console.log("##티스토리 실패##", ret);
        debugger;
    }, url);
}

function getTistoryDetail(postId, successCallBack) {
    let url = '/api/v1/tistory/' + postId;
    wbGetJson(successCallBack, function (ret) {
        console.log("##티스토리 실패##", ret);
        debugger;
    }, url);
}