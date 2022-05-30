package cadastro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;



/**
 *
 * @author curso
 */
public class Lista implements Serializable
{
    
    
    public Lista()
    {
    }
    
    Nodo pInicio = null;
    Nodo pFim = null;
    Nodo pAtual = null;
    
    
    /******************************************************************/
    
    public void Inserir(Nodo pNodo)
    
    {
        pAtual = pNodo;
        if (pInicio == null)
        {
            pInicio = pFim = pAtual;
            pAtual.pProximo = pAtual;
            pAtual.pAnterior = pAtual;
        }
        else
        {
            pFim.pProximo = pAtual;
            pAtual.pAnterior = pFim;
            pAtual.pProximo = pAtual;
            pFim = pAtual;
        }
    }
    /*************************************************/
    
    public Nodo Busca(String nomePessoa)
    {
        pAtual = pInicio;
        
        while(pAtual.pProximo != pAtual)
        {
            if (nomePessoa.equals(pAtual.pPessoa.nomePessoa))
            {
                return(pAtual);
            }
            pAtual = pAtual.pProximo;
        }
        if(nomePessoa.equals(pAtual.pPessoa.nomePessoa))
        {
            return(pAtual);
            
        }
        else
            return null;
    }
    /*************************************************/
    
    public void Retira(Nodo pNodo)
    {
        
        pAtual = pNodo;
        
       
        if(pAtual.pAnterior == pAtual)
        {
            pInicio = pAtual.pProximo;
            pInicio.pAnterior = pInicio;
            pAtual.pProximo = null;
            pAtual.pPessoa = null;
            pAtual=null;
            
        }
       
        else
            
        if (pAtual.pProximo == pAtual)
        {
            pFim = pAtual.pAnterior;
            pFim.pProximo = pFim;
            pAtual.pAnterior = null;
            pAtual.pPessoa = null;
            pAtual = null;
            
        }
        
       
        else
        if (pInicio == null)
        {
            System.out.println("Lista vazia");
            
        }
        
       
        else
        if ((pInicio != pFim))
        {
            pAtual.pAnterior.pProximo = pAtual.pProximo;
            pAtual.pProximo.pAnterior = pAtual.pAnterior;
            
            pAtual.pAnterior = null;
            pAtual.pProximo = null;
            pAtual.pPessoa = null;
            pAtual = null;
            
        }
        else
            
            if (pAtual.pAnterior==pAtual.pProximo)
            {
            pAtual.pPessoa=null;
            pAtual=null;
            pInicio=null;
            }
        
    }
    
    
    /*************************************************************/
    
    public void SalvaLista(Lista pLista)
    {
        
        
        ObjectOutputStream arq=null;
        File nomeArq = new File("teste.obj");
        
        try
        {
            arq=new ObjectOutputStream(new FileOutputStream("teste.obj"));
            arq.writeObject((Lista)pLista);
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
            
        }
        finally
        {
            try
            {
                if(arq!=null)
                    arq.close();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /************************************************************/
    
    public Lista LeLista()
    {
        
        Lista pLista=null;
        ObjectInputStream arq=null;
        
        try
                
        {
            arq=new ObjectInputStream(new FileInputStream("teste.obj"));
            pLista=(Lista) arq.readObject();
            return(pLista);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        catch (ClassNotFoundException e)
        
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(arq!=null)
                    arq.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            
        }
        return (null);
    }
    
}






