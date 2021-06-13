package DAO;

import java.util.ArrayList;
import java.util.List;

public interface DAO<T> {

	
	
	public ArrayList<T> listar();
	
	public boolean seguir(String seguido);
	
	
	
	
	
}
