// kanbanBoardList.js
function allowDrop(event) {
    event.preventDefault();
}

function dragStart(event) {
    event.dataTransfer.setData("text", event.target.id);
}

function drop(event) {
    event.preventDefault();
    var taskId = event.dataTransfer.getData("text");
    var task = document.getElementById(taskId);

    if (event.target.classList.contains('kanban_task_list')) {
        event.target.appendChild(task);
        var status = event.target.closest('.kanban_column').id;
        updateTaskStatus(taskId, status);
    }
}

function updateTaskStatus(taskId, status) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/tasks/' + taskId, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            console.log('Success:', xhr.responseText);
        } else {
            console.log('Error:', xhr.statusText);
        }
    };
    xhr.onerror = function() {
        console.log('Request failed');
    };
    var data = JSON.stringify({ taskStatus: status });
    xhr.send(data);
}

const draggableElements = document.querySelectorAll('.kanban_task');

draggableElements.forEach((draggableElement) => {
    draggableElement.addEventListener('dragstart', dragStart);
});

const dropzones = document.querySelectorAll('.kanban_task_list');

dropzones.forEach((dropzone) => {
    dropzone.addEventListener('dragover', allowDrop);
    dropzone.addEventListener('drop', drop);
});