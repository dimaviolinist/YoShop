package by.itacademy.training.yoshop.dao;

import java.util.List;

import by.itacademy.training.yoshop.entity.Model;

public interface IModelDao {

	public boolean addModel(Model model);

	public boolean updateModel(Model model);

	public boolean deleteModel(int modelId);

	public Model getModel(int modelId);

	public List<Model> getModels(int categoryId);

	public List<Model> getModels(int categoryId, int from, int pageSize);

}
