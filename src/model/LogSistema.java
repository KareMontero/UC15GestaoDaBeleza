package model;

import java.sql.Timestamp;

public class LogSistema {

    private int id;
    private String nivel;
    private String modulo;
    private String mensagem;
    private Integer usuarioId;
    private String ipOrigem;
    private Timestamp dataLog;
    private String nomeUsuario;

    public LogSistema() {
    }

    public LogSistema(int id, String nivel, String modulo, String mensagem, Integer usuarioId, String ipOrigem, Timestamp dataLog, String nomeUsuario) {
        this.id = id;
        this.nivel = nivel;
        this.modulo = modulo;
        this.mensagem = mensagem;
        this.usuarioId = usuarioId;
        this.ipOrigem = ipOrigem;
        this.dataLog = dataLog;
        this.nomeUsuario = nomeUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getIpOrigem() {
        return ipOrigem;
    }

    public void setIpOrigem(String ipOrigem) {
        this.ipOrigem = ipOrigem;
    }

    public Timestamp getDataLog() {
        return dataLog;
    }

    public void setDataLog(Timestamp dataLog) {
        this.dataLog = dataLog;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

}
