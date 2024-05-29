document.addEventListener('DOMContentLoaded', () => {
    const sideMenuItems = document.querySelectorAll('.side-menu-item, .section-content');
    const contentContainer = document.querySelector('.content-container');

    function loadContent(target, container) {
        const xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    container.innerHTML = xhr.responseText;
                    handlePageScripts(target);
                    if (target.startsWith('/project')) {
                        initNavMenuItems(container);
                    }
                } else {
                    console.error('Error loading content:', xhr.status);
                }
            }
        };
        xhr.open('GET', target, true);
        xhr.send();
    }

    function handlePageScripts(target) {
        switch (target) {
            case '/home':
                loadScript('js/project/home.js');
                break;
            case '/project/list':
                loadScript('js/project/projectList.js');
                break;
            // Add more cases for other pages
            default:
                break;
        }
    }

    function loadScript(url) {
        const script = document.createElement('script');
        script.src = url;
        document.body.appendChild(script);
    }

    function initNavMenuItems(container) {
        const navMenuItems = container.querySelectorAll('.navi-menu-item');
        const naviContentContainer = container.querySelector('.navi-content-container');

        navMenuItems.forEach(navItem => {
            navItem.addEventListener('click', () => {
                navMenuItems.forEach(item => item.classList.remove('active'));
                navItem.classList.add('active');
                const navTarget = navItem.getAttribute('data-target');
                loadContent(navTarget, naviContentContainer);
            });
        });
    }

    sideMenuItems.forEach(item => {
        item.addEventListener('click', () => {
            sideMenuItems.forEach(item => item.classList.remove('active'));
            item.classList.add('active');
            const target = item.getAttribute('data-target');
            loadContent(target, contentContainer);
        });
    });
});

document.addEventListener('DOMContentLoaded', function() {
    const topMenu = document.querySelector('.top-menu');
    const side = document.querySelector('.side');
    const contentContainer = document.querySelector('.content-container');

    // 사이드바를 기본적으로 표시
    side.classList.add('show');
    adjustContentContainerWidth();

    topMenu.addEventListener('click', function() {
        side.classList.toggle('show');
        adjustContentContainerWidth();
    });

    function adjustContentContainerWidth() {
        if (side.classList.contains('show')) {
            contentContainer.style.marginLeft = '200px';
            contentContainer.style.width = 'calc(100% - 200px)';
        } else {
            contentContainer.style.marginLeft = '0';
            contentContainer.style.width = '100%';
        }
    }
});

document.addEventListener("DOMContentLoaded", function() {
    // "홈" 버튼 자동 클릭
    document.getElementById("homeButton").click();
});

// 프로젝트 리스트 보여주기
$(document).ready(function() {
    $.ajax({
        url: "/projects",
        type: "GET",
        dataType: "json",
        success: function(data) {
            var projectList = $("#project-list");
            $.each(data, function(index, project) {
                var projectItem = $("<div>").addClass("section-content").attr("data-target", "/project?pid=" + project.pid);
                var iconSpan = $("<span>").addClass("section-icon");
                var icon = $("<i>").addClass("fa-regular fa-folder").css("color", "#" + project.pcolor);
                iconSpan.append(icon);
                var textSpan = $("<span>").addClass("section-text").text(project.pname);
                projectItem.append(iconSpan).append(textSpan);
                projectList.append(projectItem);
            });
        },
        error: function() {
            alert("프로젝트 목록을 가져오는 데 실패했습니다.");
        }
    });
    // 프로젝트 클릭 이벤트 처리
    $(document).on("click", ".section-content", function() {
        var pid = $(this).data("pid");
        var pname = $(this).find(".section-text").text();
        var pcolor = $(this).find(".fa-folder").css("color");
        window.location.href = "/project?pid=" + pid + "&pName=" + encodeURIComponent(pname) + "&pColor=" + encodeURIComponent(pcolor);
    });
});