const changeBorderColor = () => {
    let input = document.getElementById("input");

    if (input.value !== "") {
        input.setAttribute("class", "grey-input");
    }
    else {
        input.setAttribute("class", "red-input");
    }
}