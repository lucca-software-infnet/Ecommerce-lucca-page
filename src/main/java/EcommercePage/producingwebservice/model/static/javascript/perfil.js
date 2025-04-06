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


 // Recupera o token do Local Storage
 const token = localStorage.getItem('token');

 if (!token) {
     window.location.href = 'login.html'; // Redireciona para login se não estiver autenticado
 }

 // Chamada à API para obter os dados do usuário
 fetch('http://localhost:8080/users/me', {
     method: 'GET',
     headers: {
         'Authorization': 'Bearer ' + token,
         'Content-Type': 'application/json'
     }
 })
 .then(response => {
     if (response.status === 401) {
         window.location.href = 'login.html'; // Redireciona se o token for inválido
     }
     return response.json();
 })
 .then(data => {
     document.getElementById('nome').textContent = data.nome;
     document.getElementById('email').textContent = data.email;
     document.getElementById('cpf').textContent = data.cpf;
     document.getElementById('endereco').textContent = 
         `${data.endereco.logradouro}, ${data.endereco.bairro}, ${data.endereco.localidade} - ${data.endereco.uf}`;
 })
 .catch(error => console.error('Erro ao buscar os dados:', error));

 // Função para logout
 function logout() {
     localStorage.removeItem('token'); // Remove o token
     window.location.href = 'login.html'; // Redireciona para a tela de login
 }