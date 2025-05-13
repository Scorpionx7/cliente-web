# Cliente Web - Front-End

Este é o projeto front-end da aplicação de gerenciamento de clientes. Desenvolvido com **JSF** e **PrimeFaces**, o front-end serve como a interface gráfica para interação com a API RESTful do back-end.

---

## 📐 Arquitetura da Solução

O front-end foi desenvolvido para proporcionar uma experiência de uso intuitiva, responsiva e visualmente agradável. Utilizei:

- **JSF** (JavaServer Faces) para o framework de componentes
- **PrimeFaces** para componentes avançados e estilizados
- **CSS** personalizado para garantir consistência visual e responsividade

---

## 🔧 Tecnologias Utilizadas

- Java EE / Jakarta EE
- JSF
- PrimeFaces
- HTML5, CSS3
- Maven

---

## 🚀 Como Executar o Projeto

### 1. Clone o repositório

```bash
git clone https://github.com/Scorpionx7/cliente-web.git
cd cliente-web
```

### 2. Build e Deploy

Utilize o Maven para gerar o pacote WAR:

```bash
mvn clean package
```

Depois, faça o deploy do WAR gerado em um servidor de aplicação compatível (por exemplo, Apache Tomcat, WildFly ou GlassFish).

### 3. Configuração

Verifique e ajuste o arquivo `web.xml` e demais configurações, se necessário, para garantir que o contexto e as URLs dos serviços da API do back-end estejam corretos.  
Caso o endereço ou porta do back-end mude, atualize os endpoints utilizados nos beans ou arquivos de configuração.

---

## 🗺️ Funcionalidades Implementadas

- **Login:** Tela de autenticação integrando com a API. Após o login, o token JWT é armazenado na sessão e utilizado para acessar páginas protegidas. (Ainda em construção)
- **Cadastro de Clientes:** Formulário para cadastro de clientes com upload de logotipo e gerenciamento de logradouros.
- **Listagem de Clientes:** Tela para visualização, edição e exclusão de clientes.
- **Estilização Global:** Um arquivo único de CSS (`styles.css`) garante a consistência visual em todas as páginas.

> **Nota:** A implementação do front-end ainda está incompleta. Futuras melhorias incluirão otimizações de usabilidade, novos componentes e maior integração com a API.

---

## 🔗 Integração com o Back-End

Certifique-se de que o back-end da aplicação (**Cliente API**) esteja rodando e acessível.  
A comunicação é feita via endpoints REST, e o token JWT obtido no login é enviado em todas as requisições para garantir a segurança.

---

## 📦 Estrutura do Projeto

```
├── src/
│   ├── main/
│   │   ├── java/           → Beans, modelos e demais classes Java
│   │   ├── resources/      → Arquivos de configuração e mensagens
│   │   └── webapp/
│   │       ├── pages/      → Páginas JSF (login.xhtml, cliente.xhtml, listagem.xhtml, index.xhtml etc)
│   │       └── resources/  → CSS, imagens e outros arquivos estáticos
└── pom.xml
```

---

## 🚧 Futuras Melhorias

- Finalização e ajustes das páginas de cadastro e listagem
- Melhoria na validação e exibição de mensagens de erro
- Implementação de logout e controle de sessão aprimorado
- Ajustes finos na responsividade e design do layout

---

## ✍️ Desenvolvedora

Desenvolvido por **Esther**  
**Java Backend Developer**  
[LinkedIn](https://www.linkedin.com/in/estherrezende/)  
📧 Email: rezendealvesesther@gmail.com
