document.getElementById("home-dropdown-btn").addEventListener("click", function() {
  var dropdownContent = document.getElementById("home-dropdown-content");
  if (dropdownContent.style.display === "block") {
    dropdownContent.style.display = "none";
  } else {
    dropdownContent.style.display = "block";
  }
});

// 드롭다운 외부를 클릭하면 닫히도록 설정
document.addEventListener("click", function(event) {
  var dropdownContent = document.getElementById("home-dropdown-content");
  var dropdownBtn = document.getElementById("home-dropdown-btn");
  if (!event.target.matches('.home-dropdown-content') && !event.target.matches('#home-dropdown-btn')) {
    dropdownContent.style.display = "none";
  }
});
