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
public class PrototipoInterfaceDAO {
    public ArrayList<String> code = new ArrayList();
    public String title = "";
    public String classname = "";

    void define(PrototipoInterfaceDAO s, String classname, String packagename){
        String title = classname + "InterfaceDAO";
        String lower = classname.substring(0, 1).toLowerCase() + classname.substring(1);
        code.add("package " + packagename + ";\n");
        code.add("import java.awt.*;");
        code.add("import java.util.ArrayList;\n");
        code.add("public interface " + title + " {");
        code.add("public void adicionar" + classname + "(" + classname + " " + lower + ");");
        code.add("public " + classname + " buscar" + classname + "(String id);");
        code.add("public void remover" + classname + "(" + classname + " " + lower + ");");
        code.add("public void atualizar" + classname + "(" + classname + " " + lower + ");");
        code.add("public ArrayList<" + classname + "> getLista();");
        this.title = title;
        this.classname = classname;
    }

    void endLine(){
        code.add("}");
    }

    public String printCode(PrototipoInterfaceDAO s){
        String writtenCode = "";
        for(String i : s.code){
            writtenCode = writtenCode + i + "\n";
        }
        return writtenCode;
    }

}
