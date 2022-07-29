package alexsantos.databaseclient.database;

import alexsantos.databaseclient.enums.DatabasePlatform;
import oracle.jdbc.OracleDriver;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

import org.postgresql.Driver;

public class Database {
    
    private String host;
    private String port;
    private String database;
    private String user;
    private String pass;
    private Connection connection;
    private CachedRowSet cachedRowSet;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Database(String host, String port, String database, String user, String pass) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.pass = pass;
    }

    public void openConnection(DatabasePlatform dPlatform) {
        try{
            String url;
            switch (dPlatform) {
                case MYSQL -> {
                    //Class.forName("com.mysql.jdbc.Driver");
                    url = "jdbc:mysql://" + getHost() + ":" + getPort() + "/" + getDatabase() + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                    this.setConnection(DriverManager.getConnection(url, this.getUser(), this.getPass()));
                }
                case ORACLE -> {
                    //Class.forName("oracle.jdbc.driver.OracleDriver");
                    DriverManager.registerDriver(new OracleDriver());
                    url = "jdbc:oracle:thin:@//" + getHost() + ":" + getPort() + "/" + getDatabase();
                    this.setConnection(DriverManager.getConnection(url, this.getUser(), this.getPass()));
                }
                case POSTGRESQL -> {
                    DriverManager.registerDriver(new Driver());
                    url = "jdbc:postgresql://" + getHost() + ":" + getPort() + "/" + getDatabase();
                    this.setConnection(DriverManager.getConnection(url, this.getUser(), this.getPass()));
                }
                case SQLSERVER -> {
                    DriverManager.registerDriver(new SQLServerDriver());
                    url = "jdbc:sqlserver://" + getHost() + ":" + getPort() + ";databaseName=" + getDatabase();
                    this.setConnection(DriverManager.getConnection(url, this.getUser(), this.getPass()));
                }
                case H2DATABASE -> {
                    DriverManager.registerDriver(new org.h2.Driver());
                    url = "jdbc:h2:tcp://" + getHost() + "/" + getDatabase();
                    this.setConnection(DriverManager.getConnection(url, this.getUser(), this.getPass()));
                }
                default -> {}
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        try{
            if(!this.getConnection().isClosed()) {
                this.getConnection().close();
            }

            this.closeCachedRowSet();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet executeQuery() {
        try{

        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CachedRowSet select(String sql) {
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            Throwable var3 = null;

            Object var6;
            try {
                ResultSet rs = ps.executeQuery();
                Throwable var5 = null;

                try {
                    this.cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
                    this.cachedRowSet.populate(rs);

                    var6 = this.cachedRowSet;
                } catch (Throwable var31) {
                    var6 = var31;
                    var5 = var31;
                    throw var31;
                } finally {
                    if (rs != null) {
                        if (var5 != null) {
                            try {
                                rs.close();
                            } catch (Throwable var30) {
                                var5.addSuppressed(var30);
                            }
                        } else {
                            rs.close();
                        }
                    }
                }
            } catch (Throwable var33) {
                var3 = var33;
                throw var33;
            } finally {
                if (ps != null) {
                    if (var3 != null) {
                        try {
                            ps.close();
                        } catch (Throwable var29) {
                            var3.addSuppressed(var29);
                        }
                    } else {
                        ps.close();
                    }
                }
            }
            return (CachedRowSet)var6;
        } catch (SQLException var35) {
            var35.printStackTrace();
            return null;
        }
    }

    public CachedRowSet select(String sql, int autoGeneratedKeys) {
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql, autoGeneratedKeys);
            Throwable var4 = null;

            Object var7;
            try {
                ResultSet rs = ps.executeQuery();
                Throwable var6 = null;

                try {
                    this.cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
                    this.cachedRowSet.populate(rs);

                    var7 = this.cachedRowSet;
                } catch (Throwable var32) {
                    var7 = var32;
                    var6 = var32;
                    throw var32;
                } finally {
                    if (rs != null) {
                        if (var6 != null) {
                            try {
                                rs.close();
                            } catch (Throwable var31) {
                                var6.addSuppressed(var31);
                            }
                        } else {
                            rs.close();
                        }
                    }
                }
            } catch (Throwable var34) {
                var4 = var34;
                throw var34;
            } finally {
                if (ps != null) {
                    if (var4 != null) {
                        try {
                            ps.close();
                        } catch (Throwable var30) {
                            var4.addSuppressed(var30);
                        }
                    } else {
                        ps.close();
                    }
                }
            }
            return (CachedRowSet)var7;
        } catch (SQLException var36) {
            var36.printStackTrace();
            return null;
        }
    }

    public CachedRowSet select(String sql, int resultSetType, int resultSetConcurrency) {
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql, resultSetType, resultSetConcurrency);
            Throwable var5 = null;

            Object var8;
            try {
                ResultSet rs = ps.executeQuery();
                Throwable var7 = null;

                try {
                    this.cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
                    this.cachedRowSet.populate(rs);

                    var8 = this.cachedRowSet;
                } catch (Throwable var33) {
                    var8 = var33;
                    var7 = var33;
                    throw var33;
                } finally {
                    if (rs != null) {
                        if (var7 != null) {
                            try {
                                rs.close();
                            } catch (Throwable var32) {
                                var7.addSuppressed(var32);
                            }
                        } else {
                            rs.close();
                        }
                    }
                }
            } catch (Throwable var35) {
                var5 = var35;
                throw var35;
            } finally {
                if (ps != null) {
                    if (var5 != null) {
                        try {
                            ps.close();
                        } catch (Throwable var31) {
                            var5.addSuppressed(var31);
                        }
                    } else {
                        ps.close();
                    }
                }
            }

            return (CachedRowSet)var8;
        } catch (SQLException var37) {
            var37.printStackTrace();
            return null;
        }
    }

    public CachedRowSet select(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) {
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
            Throwable var6 = null;

            Object var9;
            try {
                ResultSet rs = ps.executeQuery();
                Throwable var8 = null;

                try {
                    this.cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
                    this.cachedRowSet.populate(rs);

                    var9 = this.cachedRowSet;
                } catch (Throwable var34) {
                    var9 = var34;
                    var8 = var34;
                    throw var34;
                } finally {
                    if (rs != null) {
                        if (var8 != null) {
                            try {
                                rs.close();
                            } catch (Throwable var33) {
                                var8.addSuppressed(var33);
                            }
                        } else {
                            rs.close();
                        }
                    }
                }
            } catch (Throwable var36) {
                var6 = var36;
                throw var36;
            } finally {
                if (ps != null) {
                    if (var6 != null) {
                        try {
                            ps.close();
                        } catch (Throwable var32) {
                            var6.addSuppressed(var32);
                        }
                    } else {
                        ps.close();
                    }
                }
            }

            return (CachedRowSet)var9;
        } catch (SQLException var38) {
            var38.printStackTrace();
            return null;
        }
    }

    public int executeSqlCommand(String sql) {
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql);
            Throwable var3 = null;

            int var5;
            try {
                var5 = ps.executeUpdate(sql);
            } catch (Throwable var15) {
                var3 = var15;
                throw var15;
            } finally {
                if (ps != null) {
                    if (var3 != null) {
                        try {
                            ps.close();
                        } catch (Throwable var14) {
                            var3.addSuppressed(var14);
                        }
                    } else {
                        ps.close();
                    }
                }
            }
            return var5;
        } catch (SQLException var17) {
            var17.printStackTrace();
            return 0;
        }
    }

    public int executeSqlCommand(String sql, int autoGeneratedKeys) {
        try {
            PreparedStatement ps = this.getConnection().prepareStatement(sql, autoGeneratedKeys);
            Throwable var4 = null;

            int var6;
            try {
                var6 = ps.executeUpdate(sql);
            } catch (Throwable var16) {
                var4 = var16;
                throw var16;
            } finally {
                if (ps != null) {
                    if (var4 != null) {
                        try {
                            ps.close();
                        } catch (Throwable var15) {
                            var4.addSuppressed(var15);
                        }
                    } else {
                        ps.close();
                    }
                }
            }
            return var6;
        } catch (SQLException var18) {
            var18.printStackTrace();
            return 0;
        }
    }

    private void closeCachedRowSet() {
        try {
            if (this.cachedRowSet != null) {
                this.cachedRowSet.close();
            }

            System.out.println("CachedRowSet encerrado com sucesso.");
        } catch (SQLException var2) {
            var2.printStackTrace();
        }
    }
}
