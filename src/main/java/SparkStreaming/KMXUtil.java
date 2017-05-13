package SparkStreaming;

import java.util.ArrayList;
import java.util.List;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.sagittarius.bean.common.TimePartition;
import com.sagittarius.bean.common.ValueType;
import com.sagittarius.exceptions.NoHostAvailableException;
import com.sagittarius.exceptions.QueryExecutionException;
import com.sagittarius.exceptions.TimeoutException;

import com.sagittarius.read.Reader;
import com.sagittarius.read.SagittariusReader;
import com.sagittarius.write.Writer;
import com.sagittarius.core.SagittariusClient;

public class KMXUtil {
	private static Reader reader;
	private static Writer writer;
	static{

        Cluster cluster = Cluster.builder()
                .addContactPoint("192.168.15.114")
                .withPort(9042)
                .withCredentials("cassandra", "cassandra")
                //.withQueryOptions(options)
                .build();
		SagittariusClient client = new SagittariusClient(cluster, 10000);
		reader = client.getReader();
		writer = client.getWriter();
	}	
		public static Reader getReader(){
			return reader;
		}
		
		public static Writer getWriter(){
			return writer;
		}
}

