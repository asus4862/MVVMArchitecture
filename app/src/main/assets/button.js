((w, d) => {
 const button = document.createElement("button");
 button.style["z-index"] = "1000";
 button.innerText = "button";
 button.style.position = "fixed";
 button.style.top = "80px";
 button.onclick = () => alert("clicked");
 d.body.appendChild(button)
})(window, document)
