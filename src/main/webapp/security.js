if (!sessionStorage.userId||!sessionStorage.employeeId||sessionStorage.userId!==sessionStorage.employeeId) {
    window.location.replace("/login.html")
}