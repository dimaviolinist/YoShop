package by.itacademy.training.yoshop.dao;

import java.util.List;

import by.itacademy.training.yoshop.entity.BlackListRecord;
import by.itacademy.training.yoshop.entity.User;

public interface IBanManagementDao {

	public List<BlackListRecord> getBanList();

	public boolean addUserToBanList(int id, int days);

	public boolean stopBanUser(int id);

	public List<BlackListRecord> UserBanListStory(User user);

}
