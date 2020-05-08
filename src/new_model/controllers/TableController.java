package new_model.controllers;

import new_model.dbClasses.KeyVals;
import new_model.dbClasses.SelectResult;

import java.util.ArrayList;

public class TableController extends DbController{
    protected String tableName;

    public TableController(String dataBase, String user, String password, String tableName) {
        super(dataBase, user, password);
        this.tableName = tableName;
    }

    public int count(String condition) {
        return super.countInTable(tableName, condition);
    }
    public int count(KeyVals condition) {
        return super.countInTable(tableName, condition);
    }
    public int count(String colIndex, String valIndex) {
        return super.countInTable(tableName, colIndex, valIndex);
    }
    public int count() { return count("1"); }

    public TableController(String dataBase, String tableName) {
        super(dataBase);
        this.tableName = tableName;
    }
    
    public SelectResult select(ArrayList<String> fields, String condition) {
        return super.selectFromTable(tableName, fields, condition);
    }
    public SelectResult select(ArrayList<String> fields) {
        return super.selectFromTable(tableName, fields);
    }
    public SelectResult select(String[] fields, String conition) {
        return super.selectFromTable(tableName, fields, conition);
    }
    public SelectResult select(String[] fields) {
        return super.selectFromTable(tableName, fields);
    }
    public SelectResult select(String field, String condition) {
        return super.selectFromTable(tableName, field, condition);
    }
    public KeyVals selectOne(ArrayList<String> fields, String condition) {
        SelectResult resDish = select(fields, condition);
        if (resDish.size() != 1)
            return null;
        return resDish.get(0);
    }
    public SelectResult selectFromTable(String field) {
        return super.selectFromTable(tableName, field);
    }
    public String getValue(int row, String col){
        return select(ALL).get(row).get(col);
    }

    public void delete(String condition) {
        super.deleteFromTable(tableName, condition);
    }
    public void delete(KeyVals condition) {
        super.deleteFromTable(tableName, condition);
    }
    public void delete(String colIndex, String valIndex) {
        super.deleteFromTable(tableName, colIndex, valIndex);
    }

    public void update(String setting, String condition) {
        super.updateTable(tableName, setting, condition);
    }
    public void update(KeyVals setting, String condition) {
        super.updateTable(tableName, setting, condition);
    }
    public void update(KeyVals setting, KeyVals condition) {
        super.updateTable(tableName, setting, condition);
    }
    public void update(String colUp, String newVal, String condition) {
        super.updateTable(tableName, colUp, newVal, condition);
    }
    public void update(String colUp, String newVal, KeyVals condition) {
        super.updateTable(tableName, colUp, newVal, condition);
    }
    public void update(String colUp, String newVal, String colIndex, String valIndex) {
        super.updateTable(tableName, colUp, newVal, colIndex, valIndex);
    }

    public boolean contains(String colIndex, String valIndex){
        return count(colIndex, valIndex) > 0;
    }

    public void insert(ArrayList<String> values) {
        super.insertIntoTable(tableName, values);
    }
    public void insert(String[] values) {
        super.insertIntoTable(tableName, values);
    }
}
