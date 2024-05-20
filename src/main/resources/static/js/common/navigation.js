// navigation.js
document.addEventListener('DOMContentLoaded', () => {
    const menuItems = document.querySelectorAll('.navi-menu-item');
    const contentContainer = document.querySelector('.content-container');

    menuItems.forEach(item => {
        item.addEventListener('click', () => {
            // Remove active class from all menu items
            menuItems.forEach(item => item.classList.remove('active'));

            // Add active class to the clicked menu item
            item.classList.add('active');

            // Get the target file path from the data attribute
            const target = item.getAttribute('data-target');

            // Load the content using AJAX
            const xhr = new XMLHttpRequest();
            xhr.onreadystatechange = function() {
                if (xhr.readyState === XMLHttpRequest.DONE) {
                    if (xhr.status === 200) {
                        contentContainer.innerHTML = xhr.responseText;
                    } else {
                        console.error('Error loading content:', xhr.status);
                    }
                }
            };
            xhr.open('GET', target, true);
            xhr.send();
        });
    });
});