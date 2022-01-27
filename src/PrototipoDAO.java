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
public class PrototipoDAO {
    public ArrayList<String> code = new ArrayList();
    public String title = "";
    public String classname = "";
    
    void define(PrototipoDAO s, String classname, String packagename){
        String title = classname + "DAO";
        String lower = classname.substring(0, 1).toLowerCase() + classname.substring(1);
        String lista = lower + "Lista";
        code.add("package " + packagename + ";\n");
        code.add("import java.awt.*;");
        code.add("import java.util.ArrayList;\n");
        code.add("public class " + title + " implements " + classname + "InterfaceDAO{");
        code.add("ArrayList<" + classname + "> " + lista + ";\n");
        
        //Construtor
        code.add("public " + title + "(){");
        code.add("this." + lista + " = new ArrayList<" + classname + ">();");
        code.add("}\n");
        
        //Método de adicionar:
        code.add("@Override");
        code.add("public void adicionar" + classname + "(" + classname + " novo){");
        code.add("if(!" + lista + ".contains(novo)){");
        code.add(lista + ".add(novo);}}\n");
        
        //Método de procurar:
        code.add("@Override");
        code.add("public " + classname + " buscar" + classname + "(String id){");
        code.add("for(" + classname + " x : " + lista + "){");
        code.add("if(x.getId().equals(id)){");
        code.add("return x;}}return null;}\n");
        
        //Método de remover:
        code.add("@Override");
        code.add("public void remover" + classname + "(" + classname + " selecionado){");
        code.add("if(" + lista + ".contains(selecionado)){");
        code.add(lista + ".remove(selecionado);}}\n");
        
        //Método de atualizar:
        code.add("@Override");
        code.add("public void atualizar" + classname + "(" + classname + " selecionado){");
        code.add("int indice = " + lista + ".indexOf(selecionado);");
        code.add("if(indice >= 0){");
        code.add(lista + ".remove(indice);");
        code.add(lista + ".add(selecionado);}}\n");
        
        //Método de devolver lista:
        code.add("public ArrayList<" + classname + "> getLista() {");
        code.add("return this." + lista + ";");
        code.add("}\n");
                
        this.title = title;
        this.classname = classname;
    }
    
    void endLine(){
        code.add("}");
    }
    
    public String printCode(PrototipoDAO s){
        String writtenCode = "";
        for(String i : s.code){
            writtenCode = writtenCode + i + "\n";
        }
        return writtenCode;
    }
    
    public String getTitle() {
        return title;
    }
    
}
