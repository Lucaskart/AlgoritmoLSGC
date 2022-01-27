
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lucas Lopes Fraga
 */
public class Main {
    
    public static ArrayList<String> variableTypes = new ArrayList();
    public static ArrayList<String> variableNames = new ArrayList();
    public static String packagename = "Resultados";
    
    static char checkLastValue(String string){
        char last = string.charAt(string.length() -1);
        return last;
    }
    
    static String removeLastValue(String string){
        return string.substring(0, string.length() - 1);
    }
    
    public static void main(String[] args) throws IOException {
        String nomeLSGC = "Input.lsgc";
        Arquivo arquivo = new Arquivo("src/" + nomeLSGC);
        ArrayList<String> defineDictionary = new ArrayList<>(
            Arrays.asList("define", "defina"));
        ArrayList<String> typeDictionary = new ArrayList<>(
            Arrays.asList("type", "tipo"));
        ArrayList<String> withDictionary = new ArrayList<>(
            Arrays.asList("with", "com"));
        ArrayList<String> asDictionary = new ArrayList<>(
            Arrays.asList("as", "como"));
        ArrayList<String> operationsDictionary = new ArrayList<>(
            Arrays.asList("operations", "operacoes", "operações"));
        ArrayList<String> packageDictionary = new ArrayList<>(
            Arrays.asList("namespace", "dominio", "domínio"));
        
        while (!arquivo.isEndOfFile()) {
            Arquivo arquivoclasse = new Arquivo("src/" + nomeLSGC);
            Arquivo arquivotela = new Arquivo("src/" + nomeLSGC);
            Arquivo arquivodao = new Arquivo("src/" + nomeLSGC);
            Arquivo arquivointerface = new Arquivo("src/" + nomeLSGC);
            Arquivo arquivotabela = new Arquivo("src/" + nomeLSGC);
            PrototipoTela tela = new PrototipoTela();
            PrototipoClasse classe = new PrototipoClasse();
            PrototipoDAO dao = new PrototipoDAO();
            PrototipoInterfaceDAO interfacedao = new PrototipoInterfaceDAO();
            PrototipoTableAdaptator tabela = new PrototipoTableAdaptator();
            boolean endingflag = false;
            
            String metodo = arquivo.readString().toLowerCase();
            
            // PRIMEIRO OPERADOR
            if (defineDictionary.contains(metodo)){
                String tipo = arquivo.readString().toLowerCase();
                
                // SEGUNDO OPERADOR + CLASSE
                if (typeDictionary.contains(tipo)){
                    String titulo = arquivo.readString();
                    arquivoclasse.createOutput(titulo, packagename);
                    classe.define(classe, titulo, packagename);

                    String conjuntor = arquivo.readString().toLowerCase();
                    
                    // TERCEIRO OPERADOR + VARIÁVEIS
                    if (withDictionary.contains(conjuntor)){
                        String proxVariavel = arquivo.readString();
                        while (!operationsDictionary.contains(proxVariavel.toLowerCase())){
                            String nomeVariavel = proxVariavel;
                            String proxOperator = arquivo.readString();
                            
                            // OPERADOR AUXILIAR DE VARIÁVEL
                            if (asDictionary.contains(proxOperator.toLowerCase())){
                                String tipoVariavel = arquivo.readString().toLowerCase();
                                if (checkLastValue(tipoVariavel) == ','){
                                    tipoVariavel = removeLastValue(tipoVariavel);
                                }
                                if (checkLastValue(tipoVariavel) == '.'){
                                    tipoVariavel = removeLastValue(tipoVariavel);
                                    endingflag = true;
                                }
                                
                                // TIPO DE VARIÁVEL
                                switch(tipoVariavel){
                                    case "text":
                                        tipoVariavel = "String";
                                        break;
                                    case "texto":
                                        tipoVariavel = "String";
                                        break;
                                    case "integer":
                                        tipoVariavel = "int";
                                        break;
                                    case "inteiro":
                                        tipoVariavel = "int";
                                        break;
                                    case "float":
                                        tipoVariavel = "float";
                                        break;
                                    case "decimal":
                                        tipoVariavel = "float";
                                        break;
                                    default:
                                        tipoVariavel = "String";
                                        break;
                                }
                                classe.addVariable(nomeVariavel, tipoVariavel);
                                
                                if(endingflag == true){
                                    break;
                                }
                            }
                            proxVariavel = arquivo.readString();
                        }
                        
                        classe.addConstructor();
                        classe.addGetters();
                        classe.addSetters();
                        variableNames = classe.getVariableNames();
                        variableTypes = classe.getVariableTypes();
                        tela.define(tela, titulo, packagename, variableNames, variableTypes);
                        tela.createFields(variableNames, variableTypes);
                        tela.createLayout(variableNames, variableTypes);
                        tabela.define(tabela, titulo, packagename, variableNames, variableTypes);
                        
                        // QUARTO OPERADOR + MÉTODOS
                        if(operationsDictionary.contains(proxVariavel.toLowerCase())){
                            while(endingflag == false){
                                String operacao = arquivo.readString();
                                if (checkLastValue(operacao) == '.'){
                                    operacao = removeLastValue(operacao);
                                    endingflag = true;
                                }

                                if ("crud".equals(operacao.toLowerCase())){
                                    interfacedao.define(interfacedao, titulo, packagename);
                                    dao.define(dao, titulo, packagename);
                                    arquivotela.createOutput(titulo + "Tela", packagename);
                                    arquivointerface.createOutput(titulo + "InterfaceDAO", packagename);
                                    arquivodao.createOutput(titulo + "DAO", packagename);
                                    arquivotabela.createOutput(titulo + "TableAdaptator", packagename);
                                }
                            }
                        }
                    }
                    
                    arquivo.readLine();
                    tela.endLine(tela, tela.title, variableNames, variableTypes);
                    classe.endLine();
                    interfacedao.endLine();
                    dao.endLine();
                    tabela.endLine();
                    if(classe.printCode(classe) != null){
                        arquivoclasse.print(classe.printCode(classe));
                    }
                    if(!"".equals(dao.title)){
                        arquivotela.print(tela.printCode(tela));
                        arquivointerface.print(interfacedao.printCode(interfacedao));
                        arquivodao.print(dao.printCode(dao));
                        arquivotabela.print(tabela.printCode(tabela));
                    }
                    arquivotela.close();
                    arquivoclasse.close();
                    arquivointerface.close();
                    arquivodao.close();
                    arquivotabela.close();
                    
                }
                if (packageDictionary.contains(tipo)){
                    String pacote = arquivo.readString();
                    if (checkLastValue(pacote) == '.'){
                        pacote = removeLastValue(pacote);
                        packagename = pacote;
                    }
                    packagename = pacote;
                }
                
            }
        }
    }
}
