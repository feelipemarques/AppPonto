package br.com.felipe.AppPonto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConexaoJPA {

    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpaUnit");

    public static EntityManager createEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    public static void close(){
        if(entityManagerFactory != null && entityManagerFactory.isOpen()){
            entityManagerFactory.close();
        }
    }

    public void registra(String funcional, String dia, String hora, boolean entrada){
        EntityManager em = ConexaoJPA.createEntityManager();

        em.getTransaction().begin();

        RegistroPonto registroPontoEntrada = new RegistroPonto(funcional, dia, hora);

        if(entrada){
            em.persist(registroPontoEntrada);
        }else{
            ChaveComposta chave = new ChaveComposta(funcional, dia);
            RegistroPonto registro = em.find(RegistroPonto.class, chave);
            registro.setHora_saida(hora);
        }
        em.getTransaction().commit();
    }




}
