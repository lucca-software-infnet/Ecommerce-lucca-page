let pesquisa = document.querySelector(".pesquisa");
let button = document.getElementById("botao");
let sugestoesContainer = document.getElementById("suggestions");

pesquisa.addEventListener("input", () => {
    let query = pesquisa.value.trim();
    if (query.length < 2) {
        sugestoesContainer.innerHTML = "";
        return;
    }
    
    fetch(`http://localhost:8080/api/produto/buscar?nome=${query}`)
    .then(response => response.json())
    .then(data => {
      sugestoesContainer.style.display = 'table-column';
        sugestoesContainer.innerHTML = "";
        let sugestoes = data.slice(0, 10);
        sugestoes.forEach(produto => {
            let item = document.createElement("div");
            item.classList.add("suggestion-item");
            item.textContent = produto.nome;
            item.addEventListener("click", () => {
                pesquisa.value = produto.nome;
                sugestoesContainer.innerHTML = "";
            });
            sugestoesContainer.appendChild(item);
        });
    })
    .catch(error => console.error("Erro ao buscar sugestões:", error));
});

function carregar() {
    let nomeProduto = pesquisa.value;
    window.location.href = `resultadoPesquisa.html?nome=${encodeURIComponent(nomeProduto)}`;
}

button.addEventListener("click", (event) => {
    event.preventDefault();
    carregar();
});

pesquisa.addEventListener("keypress", (event) => {
    if (event.key === "Enter") {
        event.preventDefault();
        carregar();
    }
});