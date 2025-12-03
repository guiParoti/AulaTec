# 📱 AulaTec

Aplicativo Android desenvolvido para facilitar o acompanhamento das aulas, tarefas, recomendações e perfil do aluno dos módulos da ETEC.
Construído em **Java + Android Studio**, utilizando **SQLite**, **MediaStore**, **AlertDialogs**, **Bottom Navigation**, e boas práticas de interface para estudantes das turmas **A (vermelho)** e **B (azul)**.

---

## 📌 Funcionalidades Principais

### 🏠 Home

* Exibe uma **curiosidade aleatória** sobre tecnologia ou programação sempre que o app é aberto.
* Mostra o módulo/turma atual do aluno.
* Possui acessos rápidos para as principais áreas.

---

## 📚 Lista de Aulas

* Lista automática das aulas conforme o **dia da semana atual**.
* Ajusta o conteúdo com base no módulo selecionado.
* Acesso rápido via barra de navegação.

---

## 👤 Área do Aluno

A área mais completa do app, permitindo personalização e organização.

### ✏️ Edição de Perfil

* Editar **foto do aluno** (via galeria usando MediaStore).
* Editar **nome do aluno**.
* Adicionar e editar o **e-mail institucional**, com validação `@etec.sp.gov`.

### 🗂️ Gerenciamento de Tarefas

Cada aluno (ID 1) pode:

* Criar tarefas com:

  * Título
  * Descrição
  * Seleção de data no **DatePicker**
* Editar título e descrição depois
* Marcar como **concluída** (data registrada automaticamente)
* Desmarcar caso necessário
* Excluir qualquer tarefa
* Tarefas pendentes exibem o **prazo**
* Tarefas concluídas exibem a **data de conclusão**

Armazenamento completo no SQLite:

```
tarefas(id_tarefa, titulo, descricao, status, prazo, tarefa_concluida, id_aluno)
```

---

## 🎬 Recomendações

* Recomendação de **filmes**, **jogos** e **apps** voltados para lógica, TI e criatividade.
* Navegação simples usando o módulo atual e turma.

---

## 🔧 Barra de Navegação

Disponível em todas as telas principais.
A barra permite navegar rapidamente entre:

* **Home**
* **Lista de Aulas**
* **Professores**
* **Recomendações**
* **Área do Aluno**

Ela utiliza **extras** para manter o módulo e turma ativos entre as telas.

---

## 🎨 Identidade Visual por Turma

As cores dos botões mudam automaticamente:

* **Turma A → Vermelho**
* **Turma B → Azul**

Isso deixa a interface personalizada e fácil de reconhecer pelo aluno.

---

## 💾 Banco de Dados (SQLite)

O app usa um banco local criado no login:

### Tabela `alunos`

* `id_aluno` (PRIMARY KEY)
* `nomeAluno`
* `fotoAluno` (BLOB)
* `email_institucional`

### Tabela `tarefas`

* `id_tarefa`
* `tituloTarefa`
* `descricaoTarefa`
* `status` (PENDENTE / CONCLUIDA)
* `prazo`
* `tarefa_concluida`
* `id_aluno`

---

## 🖼️ Manipulação de Imagens

* Seleção via galeria com `ACTION_PICK`
* Decodificação direta com `MediaStore.Images.Media.getBitmap()`
* Compressão JPEG antes de salvar no banco
* Conversão de BLOB → Bitmap ao carregar

---

## 🚀 Objetivo do Projeto

O **AulaTec** foi criado para facilitar a vida do estudante, centralizando:
✔ organização das tarefas
✔ acesso às aulas do dia
✔ recomendações educativas
✔ informações do aluno
✔ navegação simples e rápida

O app é ideal para apresentações escolares, portfólio e prática de Android.

---

## 🛠️ Tecnologias Utilizadas

* Android Studio
* Java
* SQLite
* MediaStore
* AlertDialog
* BottomNavigationView
* ListView + Adapter personalizado
* ConstraintLayout
* Intents com extras

---

## 📦 Estrutura Geral do Projeto

* `HomeActivity` – curiosidades e entrada do app
* `AreaAluno` – perfil, foto, email, tarefas
* `TarefaAdapter` – exibição personalizada das tarefas
* `EscolherRecomendacoes` / `RFilmes` – recomendações
* `TelaMod` – seleção de módulo
* `BarraDeNavegacao` – gerenciamento da navegação
* `DatabaseHelper` – criação e manutenção do SQLite

---

## 📝 Status do Projeto

✔ Funcional
✔ Banco funcionando
✔ Tarefas funcionando
✔ Perfil completo
✔ Recomendações
✔ Barra de navegação
✔ Último commit atualizado
⏳ Futuras melhorias poderão incluir:

* Autenticação real de aluno
* Sincronização via API
* Notificações de tarefas
* Melhorias visuais em Material Design 3

---

## 📄 Licença

Projeto educacional sem fins lucrativos.
Disponível para consulta e evolução.

---

