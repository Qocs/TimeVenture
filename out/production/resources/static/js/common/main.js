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
                loadScript('js/project/projectHome.js');
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
