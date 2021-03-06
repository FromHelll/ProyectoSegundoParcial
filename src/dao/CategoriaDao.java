package dao;

import dto.Categoria;
import java.util.ArrayList;

/**
 *
 * @author Jose Carlos Gutierrez
 */
public abstract class CategoriaDao {

    public abstract boolean insert(Categoria obj) throws Exception;

    public abstract int update(Categoria obj) throws Exception;

    public abstract void delete(int id);

    public abstract ArrayList<Categoria> getList();

    public abstract Categoria get(int id);

}
