/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
/**
 *
 * @author Lucas Lopes Fraga
 */
public class PrototipoTableAdaptator {
    public ArrayList<String> code = new ArrayList();
    public String title = "";
    public String classname = "";
    public ArrayList<String> variableNames = new ArrayList();
    
    void define(PrototipoTableAdaptator s, String classname, String packagename, ArrayList<String> variableNames, ArrayList<String> variableTypes){
        this.variableNames = variableNames;
        String title = classname + "TableAdaptator";
        String lower = classname.substring(0, 1).toLowerCase() + classname.substring(1);
        code.add("package " + packagename + ";\n");
        code.add("import java.awt.*;");
        code.add("import java.util.ArrayList;\n");
        code.add("import javax.swing.table.AbstractTableModel;\n");
        code.add("public class " + title + " extends AbstractTableModel{");
        code.add("private String [] columnname = {\"Id\", " + this.getStringedVariableNames() + "};");  
        code.add("private ArrayList<"+ classname +"> " + lower + "Lista;\n");
        this.title = title;
        this.classname = classname;
        
        code.add("public " + title + "(ArrayList<"+ classname +"> " + lower + "Lista) {");
        code.add("this." + lower + "Lista = " + lower + "Lista;");
        code.add("}\n");
        
        code.add("@Override");
        code.add("public String getColumnName(int column) {");
        code.add("if (column >= 0 && column <columnname.length) ");
        code.add("return columnname[column];");
        code.add("else return \"\";");
        code.add("}\n");
        
        code.add("@Override");
        code.add("public void setValueAt(Object aValue, int rowIndex , int columnIndex) {");
        code.add(classname + " x = "+ lower + "Lista.get(rowIndex);");
        code.add("switch(columnIndex){");
        code.add("case 0:");
        code.add("break;");
        for(int i=0; i != variableNames.size(); i++){
            code.add("case "+ (i+1) +":");
            if(null == variableTypes.get(i)){
                code.add("x.set"+ variableNames.get(i).substring(0, 1).toUpperCase() + variableNames.get(i).substring(1) + "(aValue.toString());");
            } else switch (variableTypes.get(i)) {
                case "int":
                    code.add("x.set"+ variableNames.get(i).substring(0, 1).toUpperCase() + variableNames.get(i).substring(1) + "(Integer.valueOf(aValue.toString()));");
                    break;
                case "float":
                    code.add("x.set"+ variableNames.get(i).substring(0, 1).toUpperCase() + variableNames.get(i).substring(1) + "(Float.valueOf(aValue.toString()));");
                    break;
                default:
                    code.add("x.set"+ variableNames.get(i).substring(0, 1).toUpperCase() + variableNames.get(i).substring(1) + "(aValue.toString());");
                    break;
            }
            code.add("break;");
        }
        code.add("}");
        code.add("}\n");
        
        
        code.add("@Override");
        code.add("public int getRowCount() {");
        code.add("return " + lower + "Lista.size();");
        code.add("}\n");
        
        code.add("@Override");
        code.add("public int getColumnCount() {");
        code.add("return columnname.length;");
        code.add("}\n");
        
        code.add("@Override");
        code.add("public Object getValueAt(int rowIndex, int columnIndex) {");
        code.add(classname + " x = "+ lower + "Lista.get(rowIndex);");
        code.add("switch(columnIndex){");
        code.add("case 0:");
        code.add("return x.getId();");
        for(int i=0; i != variableNames.size(); i++){
            code.add("case "+ (i+1) +":");
            code.add("return x.get"+ variableNames.get(i).substring(0, 1).toUpperCase() + variableNames.get(i).substring(1) + "();");
        }
        code.add("default:");
        code.add("return \"Something wrong happened.\";");
        code.add("}");
        code.add("}\n");
        
        code.add("@Override");
        code.add("public boolean isCellEditable(int rowIndex, int columnIndex) {");
        code.add("return true; ");
        code.add("}\n");   
    }
    
    void endLine(){
        code.add("}");
    }
    
    public String getStringedVariableNames() {
        String resultList = "";
        for(int i=0; i != variableNames.size(); i++){
            String capsName = variableNames.get(i).substring(0, 1).toUpperCase() + variableNames.get(i).substring(1);
            String formattedName = capsName.replace("_"," ");
            resultList = resultList + "\"" + formattedName + "\", ";
        }
        return resultList.substring(0, resultList.length() - 2);
    }
    
    public String printCode(PrototipoTableAdaptator s){
        String writtenCode = "";
        for(String i : s.code){
            writtenCode = writtenCode + i + "\n";
        }
        return writtenCode;
    }
    
}
