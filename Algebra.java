package Task;
import java.util.*;
public class Algebra {
public static void main(String[] args) {
    
    Scanner sc =new Scanner(System.in);    
    TaskClass TC=new TaskClass();
    System.out.println("Enter the expression");  
    String secil=sc.nextLine();    
    TC.checkOperator(secil);
    String[] arr = new String[1];
    if(TC.multiply)
    {
        arr=secil.split("[*]");
    }    
    String term1="";
    String term2="";    

    HashMap<String,Integer> Expression = new HashMap<String,Integer>();            
    if(TC.subractact || TC.addition)
    {
        Character previousCh='0';
        for(char ch1 : secil.toCharArray())
        {        
            if(TC.isnum(ch1)||TC.isletter(ch1) || ch1==94)    
            {
                term1+=""+ch1;
            }                
            else
            {            
                if(previousCh==')' && ch1 == 45)
                {
                    TC.subractact = true;
                    continue;
                }
                if(term1.length()>0)
                {                                             
                    TC.splitTerm(term1.toCharArray());
                    if(Expression.containsKey(TC.variable))
                    {
                        int value=Expression.get(TC.variable);
                        Expression.replace(TC.variable, value+TC.coefficient);                    
                    }                
                    else
                    {                 
                        Expression.put(TC.variable,TC.coefficient);                 
                    }                            
                    term1="";
                    if(ch1==45)
                    {                    
                        term1+=""+ch1;    
                    }      
                    TC.clearAll();          
                }                                                                                    
            }
            previousCh = ch1;
        }
    }
    else
    {
        for(char ch1 : arr[0].toCharArray())
        {
            if(TC.isnum(ch1)||TC.isletter(ch1) || ch1==94)    
            {
                term1+=""+ch1;
            }                
            else
            {            
                if(term1.length()>0)
                {                                             
                    TC.splitTerm(term1.toCharArray());                                                
                    TC.setTempBackup(TC.coefficient,TC.variable);
                    for(char ch2 : arr[1].toCharArray())
                    {
                        if(TC.isnum(ch2)||TC.isletter(ch2) || ch2==94)    
                        {
                            term2+=""+ch2;
                        }                
                        else
                        {            
                            if(term2.length()>0)
                            {                                             
                                TC.splitTerm(term2.toCharArray());
                                TC.multiply();
                                if(Expression.containsKey(TC.variable))
                                {
                                    int value=Expression.get(TC.variable);
                                    Expression.replace(TC.variable, value+TC.coefficient);                    
                                }
                                else
                                {
                                    Expression.put(TC.variable,TC.coefficient);
                                }                            
                                term2="";
                                if(ch2==45)
                                {       
                                    term2+=""+ch2;    
                                }                                
                                    TC.useTempBackup(TC.CoefficientTemp,TC.VariableTemp); 
                            }           
                        }
                    }                
                    term1="";
                    if(ch1==45)
                    {
                        term1+=""+ch1;    
                    }
                    TC.clearAll();;                
                }                        
            }
        }            
    }           
        Set<String> keys=Expression.keySet();
        for(String str : keys)
        {
            int value=Expression.get(str);
            if(value!=0)
            {
                if(value<0)
                {                    
                    System.out.print(value+str);                    
                }
                else
                {                    
                    System.out.print("+"+value+str);                   
                }
            }        
        }
        System.out.println();
    }     
}