package storage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.db.DatabaseTypeUtils;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import entity.AssMediaMatter;
import entity.Matter;
import entity.Media;
import entity.Rate;
import entity.Spinneret;
import entity.User;

/**
 * Database helper class used to manage the creation and upgrading of your database. This class also usually provides
 * the DAOs used by the other classes.
 */
public class DatabaseManager {
	private static final int DATABASE_VERSION = 2;
	private static final String HOST="host";
	private static final String LOGIN="login";
	private static final String PASSWD="passwd";
	private static final String DATABASE_VERSION_KEY="database_version";
	
	private static DatabaseManager manager;
    private static Logger log=LogManager.getLogger(DatabaseManager.class);
	
	private ConnectionSource source;
	private Dao<Info, Integer> infoDao;

	private Dao<User, Integer> userDao;
	private Dao<Media, Integer> mediaDao;
	private Dao<Rate, Integer> rateDao;
	private Dao<Matter, Integer> matterDao;
	private Dao<Spinneret, Integer> spinneretDao;
	private Dao<AssMediaMatter, Integer> assMediaSpinDao;
	
	
	private DatabaseManager(ConnectionSource source){
		this.source=source;
		
		try {
			TableUtils.createTableIfNotExists(source, Info.class);
			QueryBuilder<Info, Integer> queryEvent=getInfoDao().queryBuilder();
			queryEvent.where().eq("key", DATABASE_VERSION_KEY);
			Info info=queryEvent.queryForFirst();
			
			if(info==null){
				init();
				
				info=new Info();
				info.key=DATABASE_VERSION_KEY;
				info.value=Integer.toString(DATABASE_VERSION);
			}
			else{
				int lastVersion=Integer.parseInt(info.value);
				
				if(lastVersion < DATABASE_VERSION) upgrade(lastVersion, DATABASE_VERSION);
				
				info.value=Integer.toString(DATABASE_VERSION);
				getInfoDao().createOrUpdate(info);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static DatabaseManager getManager(){
		if(manager==null){
			Properties properties=new Properties();
			
			try {
				properties.load(DatabaseManager.class.getResourceAsStream("database.properties"));
				
				JdbcConnectionSource source=new JdbcConnectionSource(
						properties.getProperty(HOST),
						properties.getProperty(LOGIN),
						properties.getProperty(PASSWD),
						DatabaseTypeUtils.createDatabaseType(properties.getProperty(HOST)));
				
				manager=new DatabaseManager(source);
			} catch (IOException | SQLException e) {
				e.printStackTrace();
			}
		}
		
		return manager;
	}
	
	private void init() throws SQLException{
		log.info(this.getClass().getSimpleName(), "Init database");
		
		TableUtils.createTable(source, User.class);
		TableUtils.createTable(source, Media.class);
		TableUtils.createTable(source, Spinneret.class);
		TableUtils.createTable(source, Matter.class);
		TableUtils.createTable(source, AssMediaMatter.class);
	}
	
	private void upgrade(int lastVerison, int newVersion) throws SQLException{
		log.info(this.getClass().getSimpleName(), "Upgrade database from "+lastVerison+" to "+newVersion);
		
		TableUtils.dropTable(source, AssMediaMatter.class, true);	
		TableUtils.dropTable(source, Spinneret.class, true);
		TableUtils.dropTable(source, Matter.class, true);
		TableUtils.dropTable(source, Media.class, true);
		TableUtils.dropTable(source, User.class, true);
		
		TableUtils.createTable(source, User.class);
		TableUtils.createTable(source, Media.class);
		TableUtils.createTable(source, Spinneret.class);
		TableUtils.createTable(source, Matter.class);
		TableUtils.createTable(source, AssMediaMatter.class);
	}

	public Dao<User, Integer> getUserDao() throws SQLException {
		if(userDao==null){
			userDao=DaoManager.createDao(source, User.class);
		}
		
		return userDao;
	}

	public Dao<Media, Integer> getMediaDao() throws SQLException {
		if(mediaDao==null){
			mediaDao=DaoManager.createDao(source, Media.class);
		}
		
		return mediaDao;
	}

	public Dao<Rate, Integer> getRateDao() throws SQLException {
		if(rateDao==null){
			rateDao=DaoManager.createDao(source, Rate.class);
		}
		
		return rateDao;
	}
	
	public Dao<Matter, Integer> getMatterDao() throws SQLException {
		if(matterDao==null){
			matterDao=DaoManager.createDao(source, Matter.class);
		}
		
		return matterDao;
	}

	public Dao<Spinneret, Integer> getSpinneretDao() throws SQLException {
		if(spinneretDao==null){
			spinneretDao=DaoManager.createDao(source, Spinneret.class);
		}
		
		return spinneretDao;
	}

	public Dao<AssMediaMatter, Integer> getAssMediaSpinDao() throws SQLException {
		if(assMediaSpinDao==null){
			assMediaSpinDao=DaoManager.createDao(source, AssMediaMatter.class);
		}
		
		return assMediaSpinDao;
	}
	
	public Dao<Info, Integer> getInfoDao() throws SQLException {
		if(infoDao==null){
			infoDao=DaoManager.createDao(source, Info.class);
		}
		
		return infoDao;
	}

	private static class Info{
		@DatabaseField(generatedId=true)
		public int id;
		@DatabaseField
		public String key;
		@DatabaseField
		public String value;
	}
	
	public Rate getRate(User user, Media media){
		try {
			QueryBuilder<Rate, Integer> query=getRateDao().queryBuilder();
			query.where().eq("user", user.getId())
					.and().eq("media", media.getId());
			
			return query.queryForFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<Media> getMedia(Matter matter){
		try {
			QueryBuilder<Media, Integer> queryMedia=getMediaDao().queryBuilder();
			QueryBuilder<AssMediaMatter, Integer> queryAss=getAssMediaSpinDao().queryBuilder();
			
			queryAss.where().eq("matter", matter.getId());
			queryMedia.join(queryAss);
			
			return queryMedia.query();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<Media>();
	}
}
