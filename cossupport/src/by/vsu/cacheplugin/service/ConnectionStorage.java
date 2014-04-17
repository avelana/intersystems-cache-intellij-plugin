package by.vsu.cacheplugin.service;

import com.intersys.objects.CacheDatabase;
import com.intersys.objects.CacheException;
import com.intersys.objects.Database;
import com.intersys.xep.EventPersister;
import com.intersys.xep.PersisterFactory;
import com.intersys.xep.XEPException;

/**
 * Created by mmaya on 17.04.2014.
 */
public class ConnectionStorage {
    private String host, namespace, username, password;
    private static ConnectionStorage instance;
    private String connString;
    private Integer port;
    private Database db;
    private EventPersister persister = PersisterFactory.createPersister();

    public Database getDb() {
        return db;
    }

    private ConnectionStorage() {
    }

    public static ConnectionStorage getInstance() {
        if (instance == null) {
            instance = new ConnectionStorage();
        }
        return instance;
    }

    public String getHost() {
        return host;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConnString() {
        return connString;
    }

    public Integer getPort() {
        return port;
    }

    public EventPersister getPersister() {
        return persister;
    }

    public void setParams(String host, String namespace, String username, String password, Integer port) throws CacheException {
        this.host = host;
        this.port = port;
        this.namespace = namespace;
        this.username = username;
        this.password = password;
        this.connString = "jdbc:Cache://" + host + ":" + port + "/" + namespace;
        this.db = CacheDatabase.getDatabase(connString, username, password);
    }

    public void connectToPersister() throws XEPException {
        persister.connect(host, port, namespace, username, password);
    }

    public void disconnectFromPersister() {
        persister.close();
    }

    public void clearDatabase() {
        db = null;
    }
}
