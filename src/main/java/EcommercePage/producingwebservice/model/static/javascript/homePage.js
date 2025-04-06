let pesquisa = document.querySelector(".pesquisa");
let button = document.getElementById("botao");
let sugestoesContainer = document.getElementById("suggestions");

pesquisa.addEventListener("input", () => {
    let query = pesquisa.value.trim();
    if (query.length < 2) {
        sugestoesContainer.innerHTML = "";
        return;
    }
    
    // Faz a requisição para buscar produtos sem o token
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

// Obtém o token do usuário do localStorage
const token = localStorage.getItem('token');
const linkEntrar = document.getElementById('link-entrar');

// Verifica se há um token
if (token) {
    // Faz a requisição para obter os dados do usuário
    fetch('http://localhost:8080/users/me', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token,
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.status === 401) {
            localStorage.removeItem('token'); // Remove o token inválido
            window.location.href = 'login.html'; // Redireciona para login
            return;
        }
        return response.json();
    })
    .then(data => {
        if (data && data.nome) {
            linkEntrar.textContent = data.nome; // Exibe o nome do usuário
            linkEntrar.style.cursor = "pointer"; // Adiciona cursor de clique
            linkEntrar.addEventListener('click', function(event) {
                event.preventDefault();
                window.location.href = 'perfil.html'; // Redireciona para perfil
            });
        }
    })
    .catch(error => console.error('Erro ao buscar os dados:', error));
} else {
    // Se não houver token, mantém o link "Entrar" normal
    linkEntrar.href = 'login.html';
}

// Função para logout
function logout() {
    localStorage.removeItem('token'); // Remove o token
    window.location.href = 'login.html'; // Redireciona para login
}