package br.com.thomasgreg.front.bean;

import br.com.thomasgreg.front.dto.ClienteDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.InputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@ManagedBean
@ViewScoped
public class ListagemBean implements Serializable {

    private List<ClienteDTO> clientes;

    public void carregarClientes() {
        try {
            URL url = new URL("http://localhost:8080/api/clientes");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            InputStream is = conn.getInputStream();

            ObjectMapper mapper = new ObjectMapper();
            clientes = mapper.readValue(is, new TypeReference<List<ClienteDTO>>() {});
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void visualizarLogotipo(Long clienteId) {
        try {
            String url = "http://localhost:8080/api/clientes/" + clienteId + "/logotipo";
            java.awt.Desktop.getDesktop().browse(new URL(url).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getter
    public List<ClienteDTO> getClientes() {
        return clientes;
    }

    public void atualizarCliente(org.primefaces.event.RowEditEvent<ClienteDTO> event) {
        ClienteDTO cliente = event.getObject();

        try {
            URL url = new URL("http://localhost:8080/api/clientes/" + cliente.getId());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(cliente);

            conn.getOutputStream().write(json.getBytes());
            conn.getOutputStream().flush();

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removerCliente(Long id) {
        try {
            URL url = new URL("http://localhost:8080/api/clientes/" + id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.getResponseCode();
            conn.disconnect();
            carregarClientes(); // atualiza a tabela
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
