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

    // Ensure the drop target is a valid kanban_task_list
    if (event.target.classList.contains('kanban_task_list')) {
        event.target.appendChild(task);
        var status = event.target.closest('.kanban_column').id;
        updateTaskStatus(taskId, status);
    }
}

function updateTaskStatus(taskId, status) {
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/updateTaskStatus', true);
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
    var data = JSON.stringify({ taskId: taskId, status: status });
    xhr.send(data);
}

// 드래그하는 요소를 선택합니다.
const draggableElements = document.querySelectorAll('.kanban_task');

// 드래그 대상 요소에 드래그 이벤트를 추가합니다.
draggableElements.forEach((draggableElement) => {
    draggableElement.addEventListener('dragstart', dragStart);
});

// 드롭하는 요소를 선택합니다.
const dropzones = document.querySelectorAll('.kanban_task_list');

// 드롭 대상 요소에 드롭 이벤트를 추가합니다.
dropzones.forEach((dropzone) => {
    dropzone.addEventListener('dragover', allowDrop);
    dropzone.addEventListener('drop', drop);
});