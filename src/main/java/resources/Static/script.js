const API_URL = "http://localhost:8080/api/alunos";

document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("formAluno");
    if (form) {
        form.addEventListener("submit", async (e) => {
            e.preventDefault();
            const matricula = document.getElementById("matricula").value;
            const nome = document.getElementById("nome").value;
            const curso = document.getElementById("curso").value;

            try {
                const response = await fetch(API_URL, {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify({ matricula, nome, curso })
                });

                if (!response.ok) {
                    const error = await response.text();
                    throw new Error(error);
                }

                alert("Aluno cadastrado com sucesso!");
                window.location.href = "lista.html";
            } catch (error) {
                alert("Erro: " + error.message);
            }
        });
    }

    const tabela = document.getElementById("tabelaAlunos");
    if (tabela) {
        fetch(API_URL)
            .then(res => res.json())
            .then(alunos => {
                const tbody = tabela.querySelector("tbody");
                tbody.innerHTML = "";
                alunos.forEach(aluno => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${aluno.matricula}</td>
                        <td>${aluno.nome}</td>
                        <td>${aluno.curso}</td>
                    `;
                    tbody.appendChild(row);
                });
            });
    }
});
