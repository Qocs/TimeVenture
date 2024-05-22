function toggleSection(sectionId) {
    var section = document.getElementById(sectionId);
    var icon = document.getElementById('icon_' + sectionId);
    if (section.style.display === "none" || section.style.display === "") {
        section.style.display = "block";
        icon.classList.remove('closed');
        icon.classList.add('open');
    } else {
        section.style.display = "none";
        icon.classList.remove('open');
        icon.classList.add('closed');
    }
}

function openModal(modalId, button) {
    var modal = document.getElementById(modalId);
    var rect = button.getBoundingClientRect();
    var modalWidth = modal.offsetWidth;
    var buttonWidth = rect.width;

    modal.style.top = (rect.bottom + window.scrollY) + 'px';
    modal.style.left = (rect.left + window.scrollX + (buttonWidth / 2) - (modalWidth / 2)) + 'px';
    modal.style.display = 'block';
}

function closeModal(modalId) {
    var modal = document.getElementById(modalId);
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    var sortModal = document.getElementById('sortModal');
    var filterModal = document.getElementById('filterModal');
    if (event.target == sortModal) {
        sortModal.style.display = "none";
    }
    if (event.target == filterModal) {
        filterModal.style.display = "none";
    }
}

function sortTasks(criteria) {
    console.log('Sorting by', criteria);
    closeModal('sortModal');
}

function filterTasks(criteria) {
    console.log('Filtering by', criteria);
    closeModal('filterModal');
}