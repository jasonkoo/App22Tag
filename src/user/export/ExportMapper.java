package user.export;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.lenovo.push.bigdata.devicesdk.DeviceManager;


/**
 * Input:
 * deviceId,device对标签1的权重,device对标签2的权重,...,总计数<br><br>
 * Output:
 * null
 * 
 * @author gulei2
 *
 */
public class ExportMapper extends Mapper<LongWritable, Text, Text, Text> {
	public static int varNum = 33;
	private static String[] labels = { "考试学习", "聊天社交", "理财金融", "短信通讯", "电子书",
			"系统优化", "音乐铃声", "生活购物", "健康健美", "棋牌桌游", "其他游戏", "射击飞行", "体感动作",
			"角色策略", "益智休闲", "图册漫画", "实用工具", "经营养成", "音乐节奏", "地图导航", "新闻阅读",
			"其他应用", "桌面美化", "儿童教育", "影音视频", "商务职场", "天气日历", "体育竞速", "办公效率",
			"塔防跑酷", "网游", "拍摄美图", "移植汉化" };
	private DeviceManager dm = DeviceManager.getDefaultInstance();
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		String[] part = value.toString().split(",");
		
	}
}
