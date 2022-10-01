var tistoryList = [];


var devStackList = [
    {
        'title': 'Java',
        'url': '/image/app/stack-java.png',
    },{
        'title': 'Spring',
        'url': '/image/app/stack-spring.png',
    },{
        'title': 'AWS Lightsail',
        'url': '/image/app/stack-lightsail.png',
    },{
        'title': 'AWS Ec2',
        'url': '/image/app/stack-ec2.png',
    },{
        'title': 'Travis CI',
        'url': '/image/app/stack-travisci.png',
    },{
        'title': 'DevOn Framework',
        'url': '/image/app/stack-devon.png',
    },{
        'title': 'Python',
        'url': '/image/app/stack-python.png',
    },{
        'title': 'React',
        'url': '/image/app/stack-react.png',
    },
]

function onInit() {
    getContactList();
    appendTistoryModal(tstTitleMap);
    //tstTitleMap[id][1] -> contentDetail 값으로 티스토리 게시글의 제목에 포함되는 컨텐츠만 가져온다.

    appendDevStack();

    let tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
    let tooltipList = tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl)
    })
}

function moveBack() {
    location.href = '/';
}

function appendDevStack(){
    let html = '';
    $.each(devStackList, function (idx,item){
        html += '<div class="skill-set-div rounded-circle shadow">';
        html += '    <div style="background-image: url(\''+item.url+'\');"';
        html += '         class="skill-set-div-image rounded-circle "';
        html += '         data-bs-toggle="tooltip" data-bs-placement="top" title="'+item.title+'">';
        html += '    </div>';
        html += '</div>';
    })
    $("#skill-set-all").append(html);
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
            console.log("##티스토리 성공## tistList:", ret);
            appendSelectedList();
        }, '1');
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

function getTistoryList(successCallBack, page) {
    let url = '/api/v1/tistoryList/'+page;

    const callback = (ret)=>{
        tistoryList.push(ret.tistory.item.posts);
        let item = ret?.tistory?.item;

        if(item.count * item.page < item.totalCount) getTistoryList(successCallBack, parseInt(page) + 1);
        else successCallBack(tistoryList);
    }

    wbGetJson(callback, function (ret) {
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