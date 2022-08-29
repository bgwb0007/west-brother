function onInit() {
    getTistoryCategoryList();
}



var shoesList;
var instagramList;

let currentDroppable = null;

ball.onmousedown = function (event) {

    let shiftX = event.clientX - ball.getBoundingClientRect().left;
    let shiftY = event.clientY - ball.getBoundingClientRect().top;

    ball.style.position = 'absolute';
    ball.style.zIndex = 1000;
    document.body.append(ball);

    moveAt(event.pageX, event.pageY);

    function moveAt(pageX, pageY) {
        ball.style.left = pageX - shiftX + 'px';
        ball.style.top = pageY - shiftY + 'px';
    }

    function onMouseMove(event) {
        moveAt(event.pageX, event.pageY);

        ball.hidden = true;
        let elemBelow = document.elementFromPoint(event.clientX, event.clientY);
        ball.hidden = false;

        // 마우스 이벤트는 윈도우 밖으로 트리거 될 수 없습니다.(공을 윈도우 밖으로 드래그 했을 때)
        // clientX∙clientY가 윈도우 밖에 있으면, elementFromPoint는 null을 반환합니다.
        if (!elemBelow) return;

        // 잠재적으로 드롭 할 수 있는 요소를 'droppable' 클래스로 지정합니다.(다른 로직 가능)
        let droppableBelow = elemBelow.closest('.droppableArea');
        if (currentDroppable != droppableBelow) {
            // 들어오거나 날리거나...
            // 참고: 두 값 모두 null일 수 있습니다.
            //   currentDroppable=null 이벤트 전에 놓을 수 있는 요소 위에 있지 않다면(예: 빈 공간)
            //   droppableBelow=null 이벤트 동안 놓을 수 있는 요소 위에 있지 않다면
            if (currentDroppable) { // null when we were not over a droppable before this event
                // '날아가는 것'을 처리하는 로직(강조 제거)
                leaveDroppable(currentDroppable);

            }
            currentDroppable = droppableBelow;
            if (currentDroppable) { // null if we're not coming over a droppable now
                // (maybe just left the droppable)
                // '들어오는 것'을 처리하는 로직
                enterDroppable(currentDroppable);
            }
        }
    }

    document.addEventListener('mousemove', onMouseMove);

    ball.onmouseup = function () {
        document.removeEventListener('mousemove', onMouseMove);
        ball.onmouseup = null;
    };

};

function enterDroppable(elem) {
    elem.style.background = 'pink';
    if (elem.id == 'shoesImage') {
        onShoes();
    } else if (elem.id == 'tennisImage') {
        onTennis();
    } else if (elem.id == 'deskImage') {
        onDesk();
    }
}

function leaveDroppable(elem) {
    elem.style.background = '';
    // $('#contents').text('');
}

ball.ondragstart = function () {
    return false;
};

function onShoes() {
    $('#contents').text('');
    ball.src = '/image/app/character3.png';
    ball.width = 100;
    getShoesList('ALL');
}

function onTennis() {
    $('#contents').text('');
    ball.src = '/image/app/onTennis.png';
    ball.width = 100;
    getInstagramList();
}

function onDesk() {
    ball.src = '/image/app/character3.png' || '';
    ball.width = 100;
    devInit();
}
//################################################################
// 신발
//################################################################
function getShoesList(category) {
    if (shoesList != undefined) {
        $('#contents').text('');
        loadShoesList(sortShoesList(shoesList, category), category);
        return;
    }
    var successCallBack = function (ret) {
        $('#contents').text('');
        shoesList = ret;
        loadShoesList(sortShoesList(shoesList, category), category);
    }

    let url = '/api/v1/shoes'
    wbGetJson(successCallBack, function (ret) {
        console.log("실패함수실행##", ret);
        alert('오류로 인해 실패했습니다.');
        debugger;
    }, url);
}

function loadShoesList(list, category) {
    $('#contents').text('');
    appendShoes(list, category);
}

function appendShoes(list, category) {

    let getYield = function (item) {
        let yield = Math.round(((item.sellPrice / item.releasePrice) - 1) * 10000) / 100
        return yield >= 0 ? '+' + yield : yield;
    }
    let shoesStatus = {
        '사용중': '                            <span class="badge rounded-pill bg-success">사용중</span>',
        '리셀': '                            <span class="badge rounded-pill bg-info text-dark">리셀</span>',
        '보유중': '                            <span class="badge rounded-pill bg-warning text-dark">보유중</span>',
    }

    let html = '';
    let categoryAll = category == 'ALL' ? 'bg-primary' : 'bg-secondary';
    let categoryStock = category == 'STOCK' ? 'bg-primary' : 'bg-secondary';
    let categoryResell = category == 'RESELL' ? 'bg-primary' : 'bg-secondary';

    html += '<div id="shoesAll">';
    html += '    <div class="">';
    html += '        <div id="shoes-sortOrder">';
    html += '            <span class="shoesCategory badge rounded-pill ' + categoryAll + '">전체</span>';
    html += '            <span class="shoesCategory badge rounded-pill ' + categoryStock + '">보유</span>';
    html += '            <span class="shoesCategory badge rounded-pill ' + categoryResell + '">리셀</span>';
    html += '        </div>';
    html += '        <div class="shoes-content">';
    $.each(list, function (idx, item) {
        html += '<div class="card mb-3 shadow-sm">';
        html += '    <div class="row g-0">';
        html += '        <div class="col-md-4">';
        html += '            <img src="/images/' + item.mainImage + '" class="img-fluid rounded-start" alt="...">';
        html += '        </div>';
        html += '           <div class="col-md-8">';
        html += '               <div class="card-body">';
        html += '                   <div class="card-headder">';
        html += shoesStatus[item.status];
        // html += '                       <span></span>';
        html += '                       <small class="text-muted">' + item.purchaseDate + '</small>';
        html += '                   </div>';
        html += '                   <h5 class="card-title">' + item.brand + '</h5>';
        html += '                   <p class="card-text">' + item.name + '<br/><small class="text-muted">' + item.productCode + ' | size ' + item.size + '</small></p>';
        html += '                   <ul class="list-group list-group-flush">';
        html += '                       <li class="list-group-item">구입처 : ' + item.buy + '</li>';
        let price = item.releasePrice.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        ;
        let resell = item.sellPrice.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        ;
        html += '                       <li class="list-group-item">price : ' + price + '원 <br/> resell: ' + resell + '원 (' + getYield(item) + '%)</li>';
        html += '                       <li class="list-group-item">' + item.content + '</li>';
        html += '                   </ul>';
        html += '               </div>';
        html += '           </div>';
        html += '           <div class="col-md-12 card-thumbnail-box">';
        $.each(item.imageList, function (idx, item) {
            html += '               <img src="/images/' + item + '" class="img-fluid img-thumbnail rounded-start" alt="...">';
        })
        html += '           </div>';
        html += '       </div>';
        html += '   </div>';
    })
    html += '        </div>';
    html += '    </div>';
    html += '</div>';

    $('#contents').append(html);
    addCategoryEvent();
}

// 카테고리별 정리(전체/보유/리셀)
// -> 최신순 정렬
function sortShoesList(list, category) {
    let retList = [];
    let sCategory;
    if (category == 'ALL') {
        sCategory = '전체';
    } else if (category == 'RESELL') {
        sCategory = '리셀';
    } else if (category == 'STOCK') {
        sCategory = '사용중,보유중';
    }
    $.each(list, function (idx, item) {
        if (sCategory == '전체') {
            retList = list;
            return;
        }
        if (sCategory.indexOf(item.status) > -1) {
            retList.push(item);
        }
    })
    retList.sort(function (a, b) {
        return b.purchaseDateS8 - a.purchaseDateS8;
    })
    return retList;
}

function addCategoryEvent() {
    $('.shoesCategory').off().on('click', function (ret) {
        let selCategory = this.textContent;
        if (selCategory == '전체') {
            loadShoesList(sortShoesList(shoesList, 'ALL'), 'ALL');
        } else if (selCategory == '보유') {
            loadShoesList(sortShoesList(shoesList, 'STOCK'), 'STOCK');
        } else if (selCategory == '리셀') {
            loadShoesList(sortShoesList(shoesList, 'RESELL'), 'RESELL')
        }
    });
}

//################################################################
// 인스타
//################################################################

function getInstagramList() {
    if (instagramList != undefined) {
        $('#contents').text('');
        appendInstagram(instagramList);
        return;
    }
    var successCallBack = function (ret) {
        console.log("##인스타불러오기 성공##", ret);
        $('#contents').text('');
        instagramList = sortVideoList(ret.data);
        appendInstagram(instagramList);
    }
    let url = '/api/v1/instagram';
    wbGetJson(successCallBack, function (ret) {
        console.log("##인스타불러오기 실패##", ret);
        alert('오류로 인해 실패했습니다.');
        debugger;
    }, url);
}

function sortVideoList(list) {
    let retList = [];
    $.each(list, function (idx, item) {
        if (item.media_type == 'VIDEO') {
            retList.push(item);
        }
    });
    return retList;
}

function appendInstagram(list) {
    let html = "";
    html += '<div id="instaWrapper">';
    $.each(list, function (idx, item) {
        let caption = item.caption.replaceAll('\n', '<br>');
        html += '    <div class="instagram-All">';
        html += '        <div class="instagram-title">';
        html += '            <img src="/image/app/logo_instagram.png">';
        html += '                <span class="instagram-username" onclick="goUrl(\'' + item.permalink + '\')">' + item.username + '</span>';
        html += '        </div>';
        html += '        <div class="instagram-media">';
        html += '            <video src="' + item.media_url + '" controls id="realsVideo"></video>';
        html += '        </div>';
        html += '        <div class="instagram-content">';
        html += '            <span class="instagram-username" onclick="goUrl(\'' + item.permalink + '\')">' + item.username + '</span>';
        html += '            <span class="instagram-caption">' + caption + '</span>';
        html += '        </div>';
        html += '    </div>';
    });
    html += '</div>';
    $('#contents').append(html);
}

//################################################################
// 개발기록
//################################################################
function devInit() {
    $('#contents').empty();
    devHeaderAppend();
    document.addEventListener('scroll', devScrollFnc);
    // document.removeEventListener('scroll',devScrollFnc);

    const appendPostFnc = (tistoryList) => {
        selectedTistoryList = [...tistoryList];
        stIdx = 0;
        endIdx = 4;
        appendDevPost();
    }
    if(tistoryList) {
        appendPostFnc(tistoryList);
        return;
    }

    getTistoryList(function (ret) {
        console.log("##티스토리 성공##", ret);
        tistoryList = ret.tistory.item.posts;

        //초기화 작업!
        appendPostFnc(tistoryList);
    });


}

function devScrollFnc() {
    var maxHeight = $(document).height();
    var currentScroll = $(window).scrollTop() + $(window).height();
    if (maxHeight <= currentScroll + 100) {
        if (stIdx >= endIdx) {
            $('#dev-more-btn').hide();
            return;
        }
        //html 추가함수 실행
        appendDevPost();
    }
}


var tistoryList;
var selectedTistoryList;
var tistoryDetailMap = {}; // {게시글Id : 게시글상세 map}
var stIdx, endIdx;
var tistoryCategoryMap = {
    '프로젝트': [],
    '개발기록': []
}

function getTistoryList(successCallBack) {
    let url = '/api/v1/tistory';

    wbGetJson(successCallBack, function (ret) {
        console.log("##티스토리 실패##", ret);
        debugger;
    }, url);
}

//티스토리 게시판 카테고리 초기화 함수
function getTistoryCategoryList() {
    let url = '/api/v1/tistory/category';
    var callBack = (ret) => {
        if (ret?.tistory?.status === '200') {
            console.log("##티스토리 성공##", ret);
            let categoryList = ret.tistory.item.categories;
            $.each(categoryList, function (idx, item) {
                if (item.label.indexOf('개인') > -1) tistoryCategoryMap.프로젝트.push(item.id);
                else tistoryCategoryMap.개발기록.push(item.id);
            });
        } else console.log("##티스토리 실패##", ret);
    }
    wbGetJson(callBack, callBack, url);
}

function onDevCategory(category) {
    $('#dev-card-wrapper').empty();
    //TODO. 카테고리 변경
    $('.devCategory').each(function(ret){
        if ($(this).attr('name') == category) {
            $(this).removeClass('bg-secondary');
            $(this).addClass('bg-primary');
        }else {
            $(this).removeClass('bg-primary');
            $(this).addClass('bg-secondary');
        }
    })

    if(category=='전체') selectedTistoryList = [...tistoryList];
    else{
        selectedTistoryList = tistoryList.filter(function (content) {
            return tistoryCategoryMap[category].indexOf(content.categoryId) >= 0;
        });
    }

    stIdx = 0;
    endIdx = 4;
    appendDevPost();
}

//html 추가함수
function appendDevPost() {
    let size = selectedTistoryList.length;
    let isFinish = false;
    while (true) {
        if (stIdx >= size) {
            isFinish = true;
            endIdx = stIdx;
            break
        }
        //html 생성
        let item = selectedTistoryList[stIdx];
        let temp = item.title.split(']');
        let titleHeader = temp[0].slice(1);
        let title = temp[1];
        let datetime = item.date.split(' ')[0];
        //TODO. 컨텐츠 가져오기
        let postId = item.id;
        let content = '';

        let html = '';
        html += '<div id="dev-card-'+postId+'" class="card shadow-sm rounded">';
        html += '    <div class="card-body">';
        html += '        <div class="d-flex w-100 justify-content-between">';
        html += '            <small class="card-subtitle mb-2 ">' + titleHeader + '</small>';
        html += '            <small class="card-subtitle text-muted">' + datetime + '</small>';
        html += '        </div>';
        html += '        <h5 class="card-title">' + title + '</h5>';
        html += '        <div id="dev-card-text-'+postId+'" class="card-text text-break text-muted"></div>';
        html += '    </div>';
        html += '</div>';
        $('#dev-card-wrapper').append(html);

        if(tistoryDetailMap?.[postId]){
            getDevPostDetail(postId,(ret)=>{
                debugger;
                tistoryDetailMap[postId] = ret.tistory.item;
                let prevContent = '';
                let sIdx = ret.tistory.item.content.indexOf('<');
                let eIdx = ret.tistory.item.content.indexOf('>');
                let tempStr = content.slice(eIdx+1)
                for(let i=0; i<3; i++){
                    sIdx = tempStr.indexOf('<');
                    if(tempStr.slice(0,sIdx).length < 1) prevContent += '<pre><사진></pre><br\>';
                    else prevContent += tempStr.slice(0,sIdx) + '<br/>';

                    eIdx = tempStr.indexOf('<');
                    tempStr = tempStr.slice(eIdx+1);
                }
                tistoryDetailMap[postId].prevContent = tempStr;
                appendDevPostDetail(postId);
            });
        }else appendDevPostDetail(postId);

        stIdx++;
        if (stIdx > endIdx) {
            isFinish = true;
            endIdx += 2; // 스크롤시 두개씩 추가
            break
        }
    }
}
function appendDevPostDetail(postId){
    $('#dev-card-text-'+postId).html(tistoryDetailMap[postId].prevContent);
}

function getDevPostDetail(postId,successCallBack){
    let url = '/api/v1/tistory/'+postId;
    wbGetJson(successCallBack,function (ret){
        console.log("##티스토리 실패##",ret);
        debugger;
    },url);
}

function devHeaderAppend() {
    let html = '';
    html += '<div class="devAll">';
    html += '    <div class="dev-sortOrder">';
    html += '        <span class="devCategory badge rounded-pill bg-primary" name="전체" onclick="onDevCategory(\'전체\');">전체</span>';
    html += '        <span class="devCategory badge rounded-pill bg-secondary" name="프로젝트" onclick="onDevCategory(\'프로젝트\');">프로젝트</span>';
    html += '        <span class="devCategory badge rounded-pill bg-secondary" name="개발기록" onclick="onDevCategory(\'개발기록\');">개발기록</span>';
    html += '    </div>';
    html += '    <div id="dev-card-wrapper">';
    html += '    </div>';
    html += '    <div id="dev-more-btn"><span onclick="appendDevPost();" class="badge rounded-pill bg-light text-dark">+ 더보기</span>';
    html += '    </div>';
    html += '</div>';
    $('#contents').append(html);
}


function goUrl(url) {
    window.open(url);
}