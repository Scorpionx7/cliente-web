package br.com.thomasgreg.front.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.file.UploadedFile;

import java.io.DataOutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ClienteBean implements Serializable {

    private String nome;
    private String email;
    private UploadedFile logotipo;
    private String novoEndereco;
    private List<String> logradouros = new ArrayList<>();

    public void adicionarLogradouro() {
        if (novoEndereco != null && !novoEndereco.trim().isEmpty()) {
            logradouros.add(novoEndereco.trim());
            novoEndereco = null;
        }
    }

    public void salvar() {
        try {
            String boundary = "===" + System.currentTimeMillis() + "===";
            URL url = new URL("http://localhost:8080/api/clientes");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            DataOutputStream writer = new DataOutputStream(conn.getOutputStream());

            writeFormField(writer, boundary, "nome", nome);
            writeFormField(writer, boundary, "email", email);
            writeFormField(writer, boundary, "logradouros", logradouros.toString());

            // logotipo (arquivo)
            if (logotipo != null) {
                writer.writeBytes("--" + boundary + "\r\n");
                writer.writeBytes("Content-Disposition: form-data; name=\"logotipo\"; filename=\"" + logotipo.getFileName() + "\"\r\n");
                writer.writeBytes("Content-Type: " + logotipo.getContentType() + "\r\n\r\n");
                writer.write(logotipo.getContent());
                writer.writeBytes("\r\n");
            }

            writer.writeBytes("--" + boundary + "--\r\n");
            writer.flush();
            writer.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == 201) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente cadastrado com sucesso!", null));
                limpar();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar cliente", null));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro: " + e.getMessage(), null));
        }
    }

    private void writeFormField(DataOutputStream writer, String boundary, String name, String value) throws Exception {
        writer.writeBytes("--" + boundary + "\r\n");
        writer.writeBytes("Content-Disposition: form-data; name=\"" + name + "\"\r\n\r\n");
        writer.writeBytes(value + "\r\n");
    }

    private void limpar() {
        nome = null;
        email = null;
        logotipo = null;
        novoEndereco = null;
        logradouros.clear();
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public UploadedFile getLogotipo() { return logotipo; }
    public void setLogotipo(UploadedFile logotipo) { this.logotipo = logotipo; }
    public String getNovoEndereco() { return novoEndereco; }
    public void setNovoEndereco(String novoEndereco) { this.novoEndereco = novoEndereco; }
    public List<String> getLogradouros() { return logradouros; }
}
