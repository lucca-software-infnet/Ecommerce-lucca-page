document.addEventListener("DOMContentLoaded", function () {
    let params = new URLSearchParams(window.location.search);
    let nomeProduto = params.get("nome");

    if (nomeProduto) {
        fetch(`http://localhost:8080/api/produto/buscar?nome=${nomeProduto}`)
        .then(response => response.json())
        .then(data => {
            sugestoesContainer.style.display = 'table-column';
           
            let div = document.getElementById("product-grid");
            div.innerHTML = data.map(produto => `
                <div class="col">
                    <div class="card">
                    <img src="http://localhost:8080/api/imagem/${produto.imagem}" alt="${produto.nome}" class="card-img-top">
                        <div class="card-body">
                            <h5 class="card-title">${produto.nome}</h5>
                            <p class="card-text">Descrição: ${produto.descricao}</p>
                            <p class="card-text">R$ ${produto.valor.toFixed(2)}</p>
                        </div>
                    </div>
                </div>
                
            `).join('');
        })
        .catch(error => console.error("Erro ao carregar os produtos:", error));
    }
});

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