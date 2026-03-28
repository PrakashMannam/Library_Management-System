package services;

import interfaces.LibraryOperations;
import java.sql.Connection;
import db.DBConnection;

public abstract class BaseService implements LibraryOperations {
    protected Connection getConnection() {
        return DBConnection.getConnection();
    }
}