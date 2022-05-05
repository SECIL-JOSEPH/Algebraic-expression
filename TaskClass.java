package Task;

import java.util.*;

public class TaskClass {
    
    int coefficient = 1;
    String variable = "";
    int CoefficientTemp = 1;
    String VariableTemp = "";
    int constant=0;
    
    // char[] term1;
    // char[] term2;    
    String[] termArray;
    boolean multiply; 
    boolean subractact; 
    boolean addition; 


    // public void setTerm1(char[] value)
    // {        
    //     this.term1=value;
    // }
    // public void  setTerm2(char[] value)
    // {        
    //     this.term2=value;
    // }

    public void setTempBackup(int value1, String value2) 
    {
        this.CoefficientTemp = value1;
        this.VariableTemp = value2;                
    }          
    public void useTempBackup(int value1,String value2) 
    {
        this.coefficient = value1;
        this.variable = value2;        
    }    
    public void clearAll()
    {
        coefficient = 1;
        variable = "";
        CoefficientTemp = 1;
        VariableTemp = "";
    }
    public  boolean isnum(char ch)
    {
        boolean isnum=false;
        if((ch>=48 && ch<=57))
        {
            isnum=true;
        }
        return isnum;
    }
    public  boolean isletter(char ch)
    {
        boolean isletter=false;
        if((ch>=65 && ch<=90) || (ch>=97 && ch<=122))
        {
            isletter=true;
        }
        return isletter;
    }    
    public void checkOperator(String value)
    {
        for(int i=1; i<value.length(); i++)
        {
            if(value.charAt(i-1)==')' &&  (value.charAt(i)=='+' ||value.charAt(i)=='-'))
            {
                addition=true;
                multiply=false;
                break;
            }            
            if(value.charAt(i-1)==')' && value.charAt(i)=='*')
            {
                multiply=true;
                break;
            }
        }        
    }  
    
    public void splitTerm(char[] term)
    {
        termArray=new String[2];
        termArray[0]="";
        termArray[1]="";
        if(term[0]==45)
        {
            if(subractact)
            {
                coefficient*=1;    
            }
            else
            {
                coefficient*=-1;
            }
        }
        else
        {
            if(isnum(term[0]))
            {
                if(subractact)
                {
                    termArray[0]+="-"+term[0];
                }
                else
                {
                    termArray[0]+=""+term[0];
                }
            }
            else
            {                
                termArray[1]+=""+term[0];                
            }
        }
        
        for(int i=1;i<term.length;i++)
        {
            if(isnum(term[i]))
            {
                if(term[i-1]==94)
                {
                    termArray[1]+=""+term[i];    
                }
                else
                {
                    termArray[0]+=""+term[i];
                }
            }
            else
            {
                termArray[1]+=""+term[i];
            }            
        }        
        if(termArray[0].length()>0)
        {
            coefficient*=Integer.parseInt(termArray[0]);
        }
        variable+=termArray[1];

        if((subractact || addition) && (variable.length()==0))
        {
            constant+=coefficient;
        }
    }

    public void multiply()
    {            
        HashMap<Character,Integer> power=new HashMap<Character,Integer>();
        char previouskey='0';
        for(char ch : variable.toCharArray())
        {
            if(ch==94)
            {
                continue;
            }            
            if(power.containsKey(ch))
            {
                int value=power.get(ch);
                power.replace(ch, value+1);
            }
            else if(isnum(ch))
            {
                int value=power.get(previouskey);
                value--;                                              
                power.replace(previouskey, value+ch-48);
            }            
            else
            {
                power.put(ch, 1);                
            }
            previouskey=ch;
        }
        setVaribale(power);
    }               
    //#region Private
    private void setVaribale(HashMap<Character,Integer> map) 
    {        
        variable="";
        Set<Character> keys=map.keySet();        
        for(char ch : keys)
        {
            int value=map.get(ch);            
            if(value > 1)
            {
            variable+=ch+"^"+value;
            }
            else
            {
                variable+=ch;
            }
        }
    }
    //#endregion        
}