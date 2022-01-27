/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.UUID;
/**
 *
 * @author Lucas Lopes Fraga
 */
public class PrototipoClasse {
    public ArrayList<String> code = new ArrayList();
    public ArrayList<String> variableTypes = new ArrayList();
    public ArrayList<String> variableNames = new ArrayList();
    public String title;
    
    public PrototipoClasse() {
    }
    
    void define(PrototipoClasse s, String classname, String packagename){
        code.add("package " + packagename + ";\n");
        code.add("import java.util.ArrayList;");
        code.add("import java.util.UUID;\n");
        code.add("public class " + classname + " {");
        code.add("String id;");
        this.title = classname;
    }
    
    void addVariable(String variableName, String variableType){
        code.add(variableType + " " + variableName + ";");
        variableTypes.add(variableType);
        variableNames.add(variableName);
    }
    
    void addConstructor(){
        ArrayList<String> variables = new ArrayList();
        for(int i=0; i != variableTypes.size(); i++){
            variables.add(variableTypes.get(i) + " " + variableNames.get(i));
        }
        String variableList = String.join(", ", variables);
        code.add("\npublic " + this.title + "(" + variableList + ") {");
        for(int i=0; i != variableNames.size(); i++){
            code.add("this." + variableNames.get(i) + " = " + variableNames.get(i) + ";");
        }
        code.add("this.id = UUID.randomUUID().toString();");
        code.add("}\n");
    }

    void addGetters(){
        code.add("public String getId() {");
        code.add("return this.id;");
        code.add("}\n");
        
        for(int i=0; i != variableTypes.size(); i++){
            code.add("public " + variableTypes.get(i) + " get" + variableNames.get(i).substring(0, 1).toUpperCase() + variableNames.get(i).substring(1) + "() {");
            code.add("return this." + variableNames.get(i) + ";");
            code.add("}\n");
        }
    }

    void addSetters(){
        for(int i=0; i != variableTypes.size(); i++){
            code.add("public void set" + variableNames.get(i).substring(0, 1).toUpperCase() + variableNames.get(i).substring(1) + "("  + variableTypes.get(i) + " "  + variableNames.get(i) +  ") {");
            code.add("this." + variableNames.get(i) + " = " + variableNames.get(i)+ ";");
            code.add("}\n");
        }
    }
    
    void endLine(){
        code.add("}");
    }
    
    public String printCode(PrototipoClasse s){
        String writtenCode = "";
        for(String i : s.code){
            writtenCode = writtenCode + i + "\n";
        }
        return writtenCode;
    }

    public ArrayList<String> getVariableTypes() {
        return variableTypes;
    }

    public void setVariableTypes(ArrayList<String> newVariableTypes) {
        this.variableTypes = newVariableTypes;
    }

    public ArrayList<String> getVariableNames() {
        return variableNames;
    }

    public void setVariableNames(ArrayList<String> newVariableNames) {
        this.variableNames = newVariableNames;
    }
    
}
