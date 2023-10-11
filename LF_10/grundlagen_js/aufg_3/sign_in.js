document.addEventListener("DOMContentLoaded", () => {
    let input1 = document.getElementById("input_1");
    let input2 = document.getElementById("input_2");
    let input3 = document.getElementById("input_3");
    let button = document.getElementById("button");

    document.addEventListener("keyup", () => {
        handleInput1OnChange();
        handleInput2OnChange();
        handleInput3OnChange();
        checkForButtonAvailability();
    });

    const handleInput1OnChange = () => {
        if (input1.value !== "") {
            input1.setAttribute("class", "grey-input");
        }
        else {
            input1.setAttribute("class", "red-input");
        }
    }

    const handleInput2OnChange = () => {
        if (input2.value.length >= 8) {
            input2.setAttribute("class", "grey-input");
        }
        else {
            input2.setAttribute("class", "red-input");
        }
    }

    const handleInput3OnChange = () => {
        if (input2.value !== "" && input2.value === input3.value) {
            input3.setAttribute("class", "grey-input");
        }
        else {
            input3.setAttribute("class", "red-input");
        }
    }

    const checkForButtonAvailability = () => {
        if (input1.getAttribute("class") === "grey-input" &&
            input2.getAttribute("class") === "grey-input" &&
            input3.getAttribute("class") === "grey-input") {
            button.removeAttribute("disabled");
        }
        else {
            button.setAttribute("disabled", "true");
        }
    }
})

