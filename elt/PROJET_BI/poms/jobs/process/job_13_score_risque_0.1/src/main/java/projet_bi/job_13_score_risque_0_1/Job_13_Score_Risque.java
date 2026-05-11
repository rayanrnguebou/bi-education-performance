// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package projet_bi.job_13_score_risque_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: Job_13_Score_Risque Purpose: Job_13_Score_Risque<br>
 * Description: Job_13_Score_Risque <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class Job_13_Score_Risque implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Job_13_Score_Risque";
	private final String projectName = "PROJET_BI";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					Job_13_Score_Risque.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Job_13_Score_Risque.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_5_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_4_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_sociale_Out_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_difficulte_Out_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_finance_Out_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_3_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_3_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_4_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class sociale_OutStruct
			implements routines.system.IPersistableComparableLookupRow<sociale_OutStruct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_13_Score_Risque = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int id_integration;

		public int getId_integration() {
			return this.id_integration;
		}

		public Integer id_etudiant;

		public Integer getId_etudiant() {
			return this.id_etudiant;
		}

		public String qualite_relation_camarades;

		public String getQualite_relation_camarades() {
			return this.qualite_relation_camarades;
		}

		public String niveau_integration_sociale;

		public String getNiveau_integration_sociale() {
			return this.niveau_integration_sociale;
		}

		public Integer isolement_social;

		public Integer getIsolement_social() {
			return this.isolement_social;
		}

		public Integer victime_harcelement;

		public Integer getVictime_harcelement() {
			return this.victime_harcelement;
		}

		public String type_harcelement;

		public String getType_harcelement() {
			return this.type_harcelement;
		}

		public Integer pair_ayant_decroche_avant;

		public Integer getPair_ayant_decroche_avant() {
			return this.pair_ayant_decroche_avant;
		}

		public String qualite_relation_enseignants;

		public String getQualite_relation_enseignants() {
			return this.qualite_relation_enseignants;
		}

		public Integer beneficie_soutien_camarades;

		public Integer getBeneficie_soutien_camarades() {
			return this.beneficie_soutien_camarades;
		}

		public Integer beneficie_soutien_enseignants;

		public Integer getBeneficie_soutien_enseignants() {
			return this.beneficie_soutien_enseignants;
		}

		public String participation_activites_campus;

		public String getParticipation_activites_campus() {
			return this.participation_activites_campus;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id_etudiant == null) ? 0 : this.id_etudiant.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final sociale_OutStruct other = (sociale_OutStruct) obj;

			if (this.id_etudiant == null) {
				if (other.id_etudiant != null)
					return false;

			} else if (!this.id_etudiant.equals(other.id_etudiant))

				return false;

			return true;
		}

		public void copyDataTo(sociale_OutStruct other) {

			other.id_integration = this.id_integration;
			other.id_etudiant = this.id_etudiant;
			other.qualite_relation_camarades = this.qualite_relation_camarades;
			other.niveau_integration_sociale = this.niveau_integration_sociale;
			other.isolement_social = this.isolement_social;
			other.victime_harcelement = this.victime_harcelement;
			other.type_harcelement = this.type_harcelement;
			other.pair_ayant_decroche_avant = this.pair_ayant_decroche_avant;
			other.qualite_relation_enseignants = this.qualite_relation_enseignants;
			other.beneficie_soutien_camarades = this.beneficie_soutien_camarades;
			other.beneficie_soutien_enseignants = this.beneficie_soutien_enseignants;
			other.participation_activites_campus = this.participation_activites_campus;

		}

		public void copyKeysDataTo(sociale_OutStruct other) {

			other.id_etudiant = this.id_etudiant;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_etudiant = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_etudiant = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.id_etudiant, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.id_etudiant, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.id_integration = dis.readInt();

				this.qualite_relation_camarades = readString(dis, ois);

				this.niveau_integration_sociale = readString(dis, ois);

				this.isolement_social = readInteger(dis, ois);

				this.victime_harcelement = readInteger(dis, ois);

				this.type_harcelement = readString(dis, ois);

				this.pair_ayant_decroche_avant = readInteger(dis, ois);

				this.qualite_relation_enseignants = readString(dis, ois);

				this.beneficie_soutien_camarades = readInteger(dis, ois);

				this.beneficie_soutien_enseignants = readInteger(dis, ois);

				this.participation_activites_campus = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.id_integration = objectIn.readInt();

				this.qualite_relation_camarades = readString(dis, objectIn);

				this.niveau_integration_sociale = readString(dis, objectIn);

				this.isolement_social = readInteger(dis, objectIn);

				this.victime_harcelement = readInteger(dis, objectIn);

				this.type_harcelement = readString(dis, objectIn);

				this.pair_ayant_decroche_avant = readInteger(dis, objectIn);

				this.qualite_relation_enseignants = readString(dis, objectIn);

				this.beneficie_soutien_camarades = readInteger(dis, objectIn);

				this.beneficie_soutien_enseignants = readInteger(dis, objectIn);

				this.participation_activites_campus = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.id_integration);

				writeString(this.qualite_relation_camarades, dos, oos);

				writeString(this.niveau_integration_sociale, dos, oos);

				writeInteger(this.isolement_social, dos, oos);

				writeInteger(this.victime_harcelement, dos, oos);

				writeString(this.type_harcelement, dos, oos);

				writeInteger(this.pair_ayant_decroche_avant, dos, oos);

				writeString(this.qualite_relation_enseignants, dos, oos);

				writeInteger(this.beneficie_soutien_camarades, dos, oos);

				writeInteger(this.beneficie_soutien_enseignants, dos, oos);

				writeString(this.participation_activites_campus, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.id_integration);

				writeString(this.qualite_relation_camarades, dos, objectOut);

				writeString(this.niveau_integration_sociale, dos, objectOut);

				writeInteger(this.isolement_social, dos, objectOut);

				writeInteger(this.victime_harcelement, dos, objectOut);

				writeString(this.type_harcelement, dos, objectOut);

				writeInteger(this.pair_ayant_decroche_avant, dos, objectOut);

				writeString(this.qualite_relation_enseignants, dos, objectOut);

				writeInteger(this.beneficie_soutien_camarades, dos, objectOut);

				writeInteger(this.beneficie_soutien_enseignants, dos, objectOut);

				writeString(this.participation_activites_campus, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_integration=" + String.valueOf(id_integration));
			sb.append(",id_etudiant=" + String.valueOf(id_etudiant));
			sb.append(",qualite_relation_camarades=" + qualite_relation_camarades);
			sb.append(",niveau_integration_sociale=" + niveau_integration_sociale);
			sb.append(",isolement_social=" + String.valueOf(isolement_social));
			sb.append(",victime_harcelement=" + String.valueOf(victime_harcelement));
			sb.append(",type_harcelement=" + type_harcelement);
			sb.append(",pair_ayant_decroche_avant=" + String.valueOf(pair_ayant_decroche_avant));
			sb.append(",qualite_relation_enseignants=" + qualite_relation_enseignants);
			sb.append(",beneficie_soutien_camarades=" + String.valueOf(beneficie_soutien_camarades));
			sb.append(",beneficie_soutien_enseignants=" + String.valueOf(beneficie_soutien_enseignants));
			sb.append(",participation_activites_campus=" + participation_activites_campus);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(sociale_OutStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id_etudiant, other.id_etudiant);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_13_Score_Risque = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[0];

		public int id_integration;

		public int getId_integration() {
			return this.id_integration;
		}

		public Integer id_etudiant;

		public Integer getId_etudiant() {
			return this.id_etudiant;
		}

		public String qualite_relation_camarades;

		public String getQualite_relation_camarades() {
			return this.qualite_relation_camarades;
		}

		public String niveau_integration_sociale;

		public String getNiveau_integration_sociale() {
			return this.niveau_integration_sociale;
		}

		public Integer isolement_social;

		public Integer getIsolement_social() {
			return this.isolement_social;
		}

		public Integer victime_harcelement;

		public Integer getVictime_harcelement() {
			return this.victime_harcelement;
		}

		public String type_harcelement;

		public String getType_harcelement() {
			return this.type_harcelement;
		}

		public Integer pair_ayant_decroche_avant;

		public Integer getPair_ayant_decroche_avant() {
			return this.pair_ayant_decroche_avant;
		}

		public String qualite_relation_enseignants;

		public String getQualite_relation_enseignants() {
			return this.qualite_relation_enseignants;
		}

		public Integer beneficie_soutien_camarades;

		public Integer getBeneficie_soutien_camarades() {
			return this.beneficie_soutien_camarades;
		}

		public Integer beneficie_soutien_enseignants;

		public Integer getBeneficie_soutien_enseignants() {
			return this.beneficie_soutien_enseignants;
		}

		public String participation_activites_campus;

		public String getParticipation_activites_campus() {
			return this.participation_activites_campus;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_13_Score_Risque.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_13_Score_Risque.length == 0) {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_13_Score_Risque.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_13_Score_Risque.length == 0) {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_integration = dis.readInt();

					this.id_etudiant = readInteger(dis);

					this.qualite_relation_camarades = readString(dis);

					this.niveau_integration_sociale = readString(dis);

					this.isolement_social = readInteger(dis);

					this.victime_harcelement = readInteger(dis);

					this.type_harcelement = readString(dis);

					this.pair_ayant_decroche_avant = readInteger(dis);

					this.qualite_relation_enseignants = readString(dis);

					this.beneficie_soutien_camarades = readInteger(dis);

					this.beneficie_soutien_enseignants = readInteger(dis);

					this.participation_activites_campus = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_integration = dis.readInt();

					this.id_etudiant = readInteger(dis);

					this.qualite_relation_camarades = readString(dis);

					this.niveau_integration_sociale = readString(dis);

					this.isolement_social = readInteger(dis);

					this.victime_harcelement = readInteger(dis);

					this.type_harcelement = readString(dis);

					this.pair_ayant_decroche_avant = readInteger(dis);

					this.qualite_relation_enseignants = readString(dis);

					this.beneficie_soutien_camarades = readInteger(dis);

					this.beneficie_soutien_enseignants = readInteger(dis);

					this.participation_activites_campus = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.id_integration);

				// Integer

				writeInteger(this.id_etudiant, dos);

				// String

				writeString(this.qualite_relation_camarades, dos);

				// String

				writeString(this.niveau_integration_sociale, dos);

				// Integer

				writeInteger(this.isolement_social, dos);

				// Integer

				writeInteger(this.victime_harcelement, dos);

				// String

				writeString(this.type_harcelement, dos);

				// Integer

				writeInteger(this.pair_ayant_decroche_avant, dos);

				// String

				writeString(this.qualite_relation_enseignants, dos);

				// Integer

				writeInteger(this.beneficie_soutien_camarades, dos);

				// Integer

				writeInteger(this.beneficie_soutien_enseignants, dos);

				// String

				writeString(this.participation_activites_campus, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.id_integration);

				// Integer

				writeInteger(this.id_etudiant, dos);

				// String

				writeString(this.qualite_relation_camarades, dos);

				// String

				writeString(this.niveau_integration_sociale, dos);

				// Integer

				writeInteger(this.isolement_social, dos);

				// Integer

				writeInteger(this.victime_harcelement, dos);

				// String

				writeString(this.type_harcelement, dos);

				// Integer

				writeInteger(this.pair_ayant_decroche_avant, dos);

				// String

				writeString(this.qualite_relation_enseignants, dos);

				// Integer

				writeInteger(this.beneficie_soutien_camarades, dos);

				// Integer

				writeInteger(this.beneficie_soutien_enseignants, dos);

				// String

				writeString(this.participation_activites_campus, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_integration=" + String.valueOf(id_integration));
			sb.append(",id_etudiant=" + String.valueOf(id_etudiant));
			sb.append(",qualite_relation_camarades=" + qualite_relation_camarades);
			sb.append(",niveau_integration_sociale=" + niveau_integration_sociale);
			sb.append(",isolement_social=" + String.valueOf(isolement_social));
			sb.append(",victime_harcelement=" + String.valueOf(victime_harcelement));
			sb.append(",type_harcelement=" + type_harcelement);
			sb.append(",pair_ayant_decroche_avant=" + String.valueOf(pair_ayant_decroche_avant));
			sb.append(",qualite_relation_enseignants=" + qualite_relation_enseignants);
			sb.append(",beneficie_soutien_camarades=" + String.valueOf(beneficie_soutien_camarades));
			sb.append(",beneficie_soutien_enseignants=" + String.valueOf(beneficie_soutien_enseignants));
			sb.append(",participation_activites_campus=" + participation_activites_campus);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				sociale_OutStruct sociale_Out = new sociale_OutStruct();

				/**
				 * [tAdvancedHash_sociale_Out begin ] start
				 */

				ok_Hash.put("tAdvancedHash_sociale_Out", false);
				start_Hash.put("tAdvancedHash_sociale_Out", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_sociale_Out";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "sociale_Out");
				}

				int tos_count_tAdvancedHash_sociale_Out = 0;

				// connection name:sociale_Out
				// source node:tMap_1 - inputs:(row1) outputs:(sociale_Out,sociale_Out) | target
				// node:tAdvancedHash_sociale_Out - inputs:(sociale_Out) outputs:()
				// linked node: tMap_4 -
				// inputs:(sociale_Out,difficulte_Out,finance_Out,etudiant_Out)
				// outputs:(score_risque_Output)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_sociale_Out = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<sociale_OutStruct> tHash_Lookup_sociale_Out = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<sociale_OutStruct>getLookup(matchingModeEnum_sociale_Out);

				globalMap.put("tHash_Lookup_sociale_Out", tHash_Lookup_sociale_Out);

				/**
				 * [tAdvancedHash_sociale_Out begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				sociale_OutStruct sociale_Out_tmp = new sociale_OutStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_1 = null;
				int limit_tFileInputDelimited_1 = -1;
				try {

					Object filename_tFileInputDelimited_1 = "C:/home/claude/education_bi_v2/dim_integration_sociale.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/home/claude/education_bi_v2/dim_integration_sociale.csv", "UTF-8", ",", "\n", true,
								1, 0, limit_tFileInputDelimited_1, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_1 != null && fid_tFileInputDelimited_1.nextRecord()) {
						rowstate_tFileInputDelimited_1.reset();

						row1 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row1 = new row1Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_1 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_1 = 0;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.id_integration = ParserUtils.parseTo_int(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"id_integration", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								rowstate_tFileInputDelimited_1.setException(new RuntimeException(
										"Value is empty for column : 'id_integration' in 'row1' connection, value is invalid or this column should be nullable or have a default value."));

							}

							columnIndexWithD_tFileInputDelimited_1 = 1;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.id_etudiant = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"id_etudiant", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.id_etudiant = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 2;

							row1.qualite_relation_camarades = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 3;

							row1.niveau_integration_sociale = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.isolement_social = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"isolement_social", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.isolement_social = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 5;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.victime_harcelement = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"victime_harcelement", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.victime_harcelement = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 6;

							row1.type_harcelement = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 7;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.pair_ayant_decroche_avant = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"pair_ayant_decroche_avant", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.pair_ayant_decroche_avant = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 8;

							row1.qualite_relation_enseignants = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 9;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.beneficie_soutien_camarades = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"beneficie_soutien_camarades", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.beneficie_soutien_camarades = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 10;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.beneficie_soutien_enseignants = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"beneficie_soutien_enseignants", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.beneficie_soutien_enseignants = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 11;

							row1.participation_activites_campus = fid_tFileInputDelimited_1
									.get(columnIndexWithD_tFileInputDelimited_1);

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row1 = null;

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row1"
						if (row1 != null) {

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
								// ###############################
								// # Output tables

								sociale_Out = null;

// # Output table : 'sociale_Out'
								sociale_Out_tmp.id_integration = row1.id_integration;
								sociale_Out_tmp.id_etudiant = row1.id_etudiant;
								sociale_Out_tmp.qualite_relation_camarades = row1.qualite_relation_camarades;
								sociale_Out_tmp.niveau_integration_sociale = row1.niveau_integration_sociale;
								sociale_Out_tmp.isolement_social = row1.isolement_social;
								sociale_Out_tmp.victime_harcelement = row1.victime_harcelement;
								sociale_Out_tmp.type_harcelement = row1.type_harcelement;
								sociale_Out_tmp.pair_ayant_decroche_avant = row1.pair_ayant_decroche_avant;
								sociale_Out_tmp.qualite_relation_enseignants = row1.qualite_relation_enseignants;
								sociale_Out_tmp.beneficie_soutien_camarades = row1.beneficie_soutien_camarades;
								sociale_Out_tmp.beneficie_soutien_enseignants = row1.beneficie_soutien_enseignants;
								sociale_Out_tmp.participation_activites_campus = row1.participation_activites_campus;
								sociale_Out = sociale_Out_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_1 = false;

							tos_count_tMap_1++;

							/**
							 * [tMap_1 main ] stop
							 */

							/**
							 * [tMap_1 process_data_begin ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_begin ] stop
							 */
// Start of branch "sociale_Out"
							if (sociale_Out != null) {

								/**
								 * [tAdvancedHash_sociale_Out main ] start
								 */

								currentComponent = "tAdvancedHash_sociale_Out";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "sociale_Out"

									);
								}

								sociale_OutStruct sociale_Out_HashRow = new sociale_OutStruct();

								sociale_Out_HashRow.id_integration = sociale_Out.id_integration;

								sociale_Out_HashRow.id_etudiant = sociale_Out.id_etudiant;

								sociale_Out_HashRow.qualite_relation_camarades = sociale_Out.qualite_relation_camarades;

								sociale_Out_HashRow.niveau_integration_sociale = sociale_Out.niveau_integration_sociale;

								sociale_Out_HashRow.isolement_social = sociale_Out.isolement_social;

								sociale_Out_HashRow.victime_harcelement = sociale_Out.victime_harcelement;

								sociale_Out_HashRow.type_harcelement = sociale_Out.type_harcelement;

								sociale_Out_HashRow.pair_ayant_decroche_avant = sociale_Out.pair_ayant_decroche_avant;

								sociale_Out_HashRow.qualite_relation_enseignants = sociale_Out.qualite_relation_enseignants;

								sociale_Out_HashRow.beneficie_soutien_camarades = sociale_Out.beneficie_soutien_camarades;

								sociale_Out_HashRow.beneficie_soutien_enseignants = sociale_Out.beneficie_soutien_enseignants;

								sociale_Out_HashRow.participation_activites_campus = sociale_Out.participation_activites_campus;

								tHash_Lookup_sociale_Out.put(sociale_Out_HashRow);

								tos_count_tAdvancedHash_sociale_Out++;

								/**
								 * [tAdvancedHash_sociale_Out main ] stop
								 */

								/**
								 * [tAdvancedHash_sociale_Out process_data_begin ] start
								 */

								currentComponent = "tAdvancedHash_sociale_Out";

								/**
								 * [tAdvancedHash_sociale_Out process_data_begin ] stop
								 */

								/**
								 * [tAdvancedHash_sociale_Out process_data_end ] start
								 */

								currentComponent = "tAdvancedHash_sociale_Out";

								/**
								 * [tAdvancedHash_sociale_Out process_data_end ] stop
								 */

							} // End of branch "sociale_Out"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

					}
				} finally {
					if (!((Object) ("C:/home/claude/education_bi_v2/dim_integration_sociale.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_1 != null) {
							fid_tFileInputDelimited_1.close();
						}
					}
					if (fid_tFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", fid_tFileInputDelimited_1.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tAdvancedHash_sociale_Out end ] start
				 */

				currentComponent = "tAdvancedHash_sociale_Out";

				tHash_Lookup_sociale_Out.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "sociale_Out");
				}

				ok_Hash.put("tAdvancedHash_sociale_Out", true);
				end_Hash.put("tAdvancedHash_sociale_Out", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_sociale_Out end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tAdvancedHash_sociale_Out finally ] start
				 */

				currentComponent = "tAdvancedHash_sociale_Out";

				/**
				 * [tAdvancedHash_sociale_Out finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public static class difficulte_OutStruct
			implements routines.system.IPersistableComparableLookupRow<difficulte_OutStruct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_13_Score_Risque = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int id_difficulte;

		public int getId_difficulte() {
			return this.id_difficulte;
		}

		public Integer id_etudiant;

		public Integer getId_etudiant() {
			return this.id_etudiant;
		}

		public String maitrise_metier_etudiant;

		public String getMaitrise_metier_etudiant() {
			return this.maitrise_metier_etudiant;
		}

		public String difficulte_adaptation_methodes;

		public String getDifficulte_adaptation_methodes() {
			return this.difficulte_adaptation_methodes;
		}

		public Integer difficulte_taille_groupes;

		public Integer getDifficulte_taille_groupes() {
			return this.difficulte_taille_groupes;
		}

		public String interet_lecture_ecriture;

		public String getInteret_lecture_ecriture() {
			return this.interet_lecture_ecriture;
		}

		public String frequence_etude_lecons;

		public String getFrequence_etude_lecons() {
			return this.frequence_etude_lecons;
		}

		public Integer attente_veille_examens;

		public Integer getAttente_veille_examens() {
			return this.attente_veille_examens;
		}

		public String participation_examens_semestriels;

		public String getParticipation_examens_semestriels() {
			return this.participation_examens_semestriels;
		}

		public Integer passe_scolaire_difficile;

		public Integer getPasse_scolaire_difficile() {
			return this.passe_scolaire_difficile;
		}

		public Integer decrochage_anterieur_sec_prim;

		public Integer getDecrochage_anterieur_sec_prim() {
			return this.decrochage_anterieur_sec_prim;
		}

		public String difficultes_pedagogiques_specifiques;

		public String getDifficultes_pedagogiques_specifiques() {
			return this.difficultes_pedagogiques_specifiques;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id_etudiant == null) ? 0 : this.id_etudiant.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final difficulte_OutStruct other = (difficulte_OutStruct) obj;

			if (this.id_etudiant == null) {
				if (other.id_etudiant != null)
					return false;

			} else if (!this.id_etudiant.equals(other.id_etudiant))

				return false;

			return true;
		}

		public void copyDataTo(difficulte_OutStruct other) {

			other.id_difficulte = this.id_difficulte;
			other.id_etudiant = this.id_etudiant;
			other.maitrise_metier_etudiant = this.maitrise_metier_etudiant;
			other.difficulte_adaptation_methodes = this.difficulte_adaptation_methodes;
			other.difficulte_taille_groupes = this.difficulte_taille_groupes;
			other.interet_lecture_ecriture = this.interet_lecture_ecriture;
			other.frequence_etude_lecons = this.frequence_etude_lecons;
			other.attente_veille_examens = this.attente_veille_examens;
			other.participation_examens_semestriels = this.participation_examens_semestriels;
			other.passe_scolaire_difficile = this.passe_scolaire_difficile;
			other.decrochage_anterieur_sec_prim = this.decrochage_anterieur_sec_prim;
			other.difficultes_pedagogiques_specifiques = this.difficultes_pedagogiques_specifiques;

		}

		public void copyKeysDataTo(difficulte_OutStruct other) {

			other.id_etudiant = this.id_etudiant;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_etudiant = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_etudiant = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.id_etudiant, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.id_etudiant, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.id_difficulte = dis.readInt();

				this.maitrise_metier_etudiant = readString(dis, ois);

				this.difficulte_adaptation_methodes = readString(dis, ois);

				this.difficulte_taille_groupes = readInteger(dis, ois);

				this.interet_lecture_ecriture = readString(dis, ois);

				this.frequence_etude_lecons = readString(dis, ois);

				this.attente_veille_examens = readInteger(dis, ois);

				this.participation_examens_semestriels = readString(dis, ois);

				this.passe_scolaire_difficile = readInteger(dis, ois);

				this.decrochage_anterieur_sec_prim = readInteger(dis, ois);

				this.difficultes_pedagogiques_specifiques = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.id_difficulte = objectIn.readInt();

				this.maitrise_metier_etudiant = readString(dis, objectIn);

				this.difficulte_adaptation_methodes = readString(dis, objectIn);

				this.difficulte_taille_groupes = readInteger(dis, objectIn);

				this.interet_lecture_ecriture = readString(dis, objectIn);

				this.frequence_etude_lecons = readString(dis, objectIn);

				this.attente_veille_examens = readInteger(dis, objectIn);

				this.participation_examens_semestriels = readString(dis, objectIn);

				this.passe_scolaire_difficile = readInteger(dis, objectIn);

				this.decrochage_anterieur_sec_prim = readInteger(dis, objectIn);

				this.difficultes_pedagogiques_specifiques = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.id_difficulte);

				writeString(this.maitrise_metier_etudiant, dos, oos);

				writeString(this.difficulte_adaptation_methodes, dos, oos);

				writeInteger(this.difficulte_taille_groupes, dos, oos);

				writeString(this.interet_lecture_ecriture, dos, oos);

				writeString(this.frequence_etude_lecons, dos, oos);

				writeInteger(this.attente_veille_examens, dos, oos);

				writeString(this.participation_examens_semestriels, dos, oos);

				writeInteger(this.passe_scolaire_difficile, dos, oos);

				writeInteger(this.decrochage_anterieur_sec_prim, dos, oos);

				writeString(this.difficultes_pedagogiques_specifiques, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.id_difficulte);

				writeString(this.maitrise_metier_etudiant, dos, objectOut);

				writeString(this.difficulte_adaptation_methodes, dos, objectOut);

				writeInteger(this.difficulte_taille_groupes, dos, objectOut);

				writeString(this.interet_lecture_ecriture, dos, objectOut);

				writeString(this.frequence_etude_lecons, dos, objectOut);

				writeInteger(this.attente_veille_examens, dos, objectOut);

				writeString(this.participation_examens_semestriels, dos, objectOut);

				writeInteger(this.passe_scolaire_difficile, dos, objectOut);

				writeInteger(this.decrochage_anterieur_sec_prim, dos, objectOut);

				writeString(this.difficultes_pedagogiques_specifiques, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_difficulte=" + String.valueOf(id_difficulte));
			sb.append(",id_etudiant=" + String.valueOf(id_etudiant));
			sb.append(",maitrise_metier_etudiant=" + maitrise_metier_etudiant);
			sb.append(",difficulte_adaptation_methodes=" + difficulte_adaptation_methodes);
			sb.append(",difficulte_taille_groupes=" + String.valueOf(difficulte_taille_groupes));
			sb.append(",interet_lecture_ecriture=" + interet_lecture_ecriture);
			sb.append(",frequence_etude_lecons=" + frequence_etude_lecons);
			sb.append(",attente_veille_examens=" + String.valueOf(attente_veille_examens));
			sb.append(",participation_examens_semestriels=" + participation_examens_semestriels);
			sb.append(",passe_scolaire_difficile=" + String.valueOf(passe_scolaire_difficile));
			sb.append(",decrochage_anterieur_sec_prim=" + String.valueOf(decrochage_anterieur_sec_prim));
			sb.append(",difficultes_pedagogiques_specifiques=" + difficultes_pedagogiques_specifiques);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(difficulte_OutStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id_etudiant, other.id_etudiant);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_13_Score_Risque = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[0];

		public int id_difficulte;

		public int getId_difficulte() {
			return this.id_difficulte;
		}

		public Integer id_etudiant;

		public Integer getId_etudiant() {
			return this.id_etudiant;
		}

		public String maitrise_metier_etudiant;

		public String getMaitrise_metier_etudiant() {
			return this.maitrise_metier_etudiant;
		}

		public String difficulte_adaptation_methodes;

		public String getDifficulte_adaptation_methodes() {
			return this.difficulte_adaptation_methodes;
		}

		public Integer difficulte_taille_groupes;

		public Integer getDifficulte_taille_groupes() {
			return this.difficulte_taille_groupes;
		}

		public String interet_lecture_ecriture;

		public String getInteret_lecture_ecriture() {
			return this.interet_lecture_ecriture;
		}

		public String frequence_etude_lecons;

		public String getFrequence_etude_lecons() {
			return this.frequence_etude_lecons;
		}

		public Integer attente_veille_examens;

		public Integer getAttente_veille_examens() {
			return this.attente_veille_examens;
		}

		public String participation_examens_semestriels;

		public String getParticipation_examens_semestriels() {
			return this.participation_examens_semestriels;
		}

		public Integer passe_scolaire_difficile;

		public Integer getPasse_scolaire_difficile() {
			return this.passe_scolaire_difficile;
		}

		public Integer decrochage_anterieur_sec_prim;

		public Integer getDecrochage_anterieur_sec_prim() {
			return this.decrochage_anterieur_sec_prim;
		}

		public String difficultes_pedagogiques_specifiques;

		public String getDifficultes_pedagogiques_specifiques() {
			return this.difficultes_pedagogiques_specifiques;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_13_Score_Risque.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_13_Score_Risque.length == 0) {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_13_Score_Risque.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_13_Score_Risque.length == 0) {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_difficulte = dis.readInt();

					this.id_etudiant = readInteger(dis);

					this.maitrise_metier_etudiant = readString(dis);

					this.difficulte_adaptation_methodes = readString(dis);

					this.difficulte_taille_groupes = readInteger(dis);

					this.interet_lecture_ecriture = readString(dis);

					this.frequence_etude_lecons = readString(dis);

					this.attente_veille_examens = readInteger(dis);

					this.participation_examens_semestriels = readString(dis);

					this.passe_scolaire_difficile = readInteger(dis);

					this.decrochage_anterieur_sec_prim = readInteger(dis);

					this.difficultes_pedagogiques_specifiques = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_difficulte = dis.readInt();

					this.id_etudiant = readInteger(dis);

					this.maitrise_metier_etudiant = readString(dis);

					this.difficulte_adaptation_methodes = readString(dis);

					this.difficulte_taille_groupes = readInteger(dis);

					this.interet_lecture_ecriture = readString(dis);

					this.frequence_etude_lecons = readString(dis);

					this.attente_veille_examens = readInteger(dis);

					this.participation_examens_semestriels = readString(dis);

					this.passe_scolaire_difficile = readInteger(dis);

					this.decrochage_anterieur_sec_prim = readInteger(dis);

					this.difficultes_pedagogiques_specifiques = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.id_difficulte);

				// Integer

				writeInteger(this.id_etudiant, dos);

				// String

				writeString(this.maitrise_metier_etudiant, dos);

				// String

				writeString(this.difficulte_adaptation_methodes, dos);

				// Integer

				writeInteger(this.difficulte_taille_groupes, dos);

				// String

				writeString(this.interet_lecture_ecriture, dos);

				// String

				writeString(this.frequence_etude_lecons, dos);

				// Integer

				writeInteger(this.attente_veille_examens, dos);

				// String

				writeString(this.participation_examens_semestriels, dos);

				// Integer

				writeInteger(this.passe_scolaire_difficile, dos);

				// Integer

				writeInteger(this.decrochage_anterieur_sec_prim, dos);

				// String

				writeString(this.difficultes_pedagogiques_specifiques, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.id_difficulte);

				// Integer

				writeInteger(this.id_etudiant, dos);

				// String

				writeString(this.maitrise_metier_etudiant, dos);

				// String

				writeString(this.difficulte_adaptation_methodes, dos);

				// Integer

				writeInteger(this.difficulte_taille_groupes, dos);

				// String

				writeString(this.interet_lecture_ecriture, dos);

				// String

				writeString(this.frequence_etude_lecons, dos);

				// Integer

				writeInteger(this.attente_veille_examens, dos);

				// String

				writeString(this.participation_examens_semestriels, dos);

				// Integer

				writeInteger(this.passe_scolaire_difficile, dos);

				// Integer

				writeInteger(this.decrochage_anterieur_sec_prim, dos);

				// String

				writeString(this.difficultes_pedagogiques_specifiques, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_difficulte=" + String.valueOf(id_difficulte));
			sb.append(",id_etudiant=" + String.valueOf(id_etudiant));
			sb.append(",maitrise_metier_etudiant=" + maitrise_metier_etudiant);
			sb.append(",difficulte_adaptation_methodes=" + difficulte_adaptation_methodes);
			sb.append(",difficulte_taille_groupes=" + String.valueOf(difficulte_taille_groupes));
			sb.append(",interet_lecture_ecriture=" + interet_lecture_ecriture);
			sb.append(",frequence_etude_lecons=" + frequence_etude_lecons);
			sb.append(",attente_veille_examens=" + String.valueOf(attente_veille_examens));
			sb.append(",participation_examens_semestriels=" + participation_examens_semestriels);
			sb.append(",passe_scolaire_difficile=" + String.valueOf(passe_scolaire_difficile));
			sb.append(",decrochage_anterieur_sec_prim=" + String.valueOf(decrochage_anterieur_sec_prim));
			sb.append(",difficultes_pedagogiques_specifiques=" + difficultes_pedagogiques_specifiques);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row2Struct row2 = new row2Struct();
				difficulte_OutStruct difficulte_Out = new difficulte_OutStruct();

				/**
				 * [tAdvancedHash_difficulte_Out begin ] start
				 */

				ok_Hash.put("tAdvancedHash_difficulte_Out", false);
				start_Hash.put("tAdvancedHash_difficulte_Out", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_difficulte_Out";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "difficulte_Out");
				}

				int tos_count_tAdvancedHash_difficulte_Out = 0;

				// connection name:difficulte_Out
				// source node:tMap_2 - inputs:(row2) outputs:(difficulte_Out,difficulte_Out) |
				// target node:tAdvancedHash_difficulte_Out - inputs:(difficulte_Out) outputs:()
				// linked node: tMap_4 -
				// inputs:(sociale_Out,difficulte_Out,finance_Out,etudiant_Out)
				// outputs:(score_risque_Output)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_difficulte_Out = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<difficulte_OutStruct> tHash_Lookup_difficulte_Out = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<difficulte_OutStruct>getLookup(matchingModeEnum_difficulte_Out);

				globalMap.put("tHash_Lookup_difficulte_Out", tHash_Lookup_difficulte_Out);

				/**
				 * [tAdvancedHash_difficulte_Out begin ] stop
				 */

				/**
				 * [tMap_2 begin ] start
				 */

				ok_Hash.put("tMap_2", false);
				start_Hash.put("tMap_2", System.currentTimeMillis());

				currentComponent = "tMap_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tMap_2 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_2__Struct {
				}
				Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
				difficulte_OutStruct difficulte_Out_tmp = new difficulte_OutStruct();
// ###############################

				/**
				 * [tMap_2 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_2", false);
				start_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_2";

				int tos_count_tFileInputDelimited_2 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_2 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_2 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_2 = null;
				int limit_tFileInputDelimited_2 = -1;
				try {

					Object filename_tFileInputDelimited_2 = "C:/home/claude/education_bi_v2/dim_difficultes_academiques.csv";
					if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_2 = 0, random_value_tFileInputDelimited_2 = -1;
						if (footer_value_tFileInputDelimited_2 > 0 || random_value_tFileInputDelimited_2 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_2 = new org.talend.fileprocess.FileInputDelimited(
								"C:/home/claude/education_bi_v2/dim_difficultes_academiques.csv", "UTF-8", ",", "\n",
								true, 1, 0, limit_tFileInputDelimited_2, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_2 != null && fid_tFileInputDelimited_2.nextRecord()) {
						rowstate_tFileInputDelimited_2.reset();

						row2 = null;

						boolean whetherReject_tFileInputDelimited_2 = false;
						row2 = new row2Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_2 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_2 = 0;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.id_difficulte = ParserUtils.parseTo_int(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"id_difficulte", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								rowstate_tFileInputDelimited_2.setException(new RuntimeException(
										"Value is empty for column : 'id_difficulte' in 'row2' connection, value is invalid or this column should be nullable or have a default value."));

							}

							columnIndexWithD_tFileInputDelimited_2 = 1;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.id_etudiant = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"id_etudiant", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.id_etudiant = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 2;

							row2.maitrise_metier_etudiant = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 3;

							row2.difficulte_adaptation_methodes = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 4;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.difficulte_taille_groupes = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"difficulte_taille_groupes", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.difficulte_taille_groupes = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 5;

							row2.interet_lecture_ecriture = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 6;

							row2.frequence_etude_lecons = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 7;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.attente_veille_examens = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"attente_veille_examens", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.attente_veille_examens = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 8;

							row2.participation_examens_semestriels = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 9;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.passe_scolaire_difficile = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"passe_scolaire_difficile", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.passe_scolaire_difficile = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 10;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.decrochage_anterieur_sec_prim = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"decrochage_anterieur_sec_prim", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.decrochage_anterieur_sec_prim = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 11;

							row2.difficultes_pedagogiques_specifiques = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							if (rowstate_tFileInputDelimited_2.getException() != null) {
								throw rowstate_tFileInputDelimited_2.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_2 = true;

							System.err.println(e.getMessage());
							row2 = null;

						}

						/**
						 * [tFileInputDelimited_2 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_2 main ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						tos_count_tFileInputDelimited_2++;

						/**
						 * [tFileInputDelimited_2 main ] stop
						 */

						/**
						 * [tFileInputDelimited_2 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_begin ] stop
						 */
// Start of branch "row2"
						if (row2 != null) {

							/**
							 * [tMap_2 main ] start
							 */

							currentComponent = "tMap_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row2"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_2 = false;
							boolean mainRowRejected_tMap_2 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_2__Struct Var = Var__tMap_2;// ###############################
								// ###############################
								// # Output tables

								difficulte_Out = null;

// # Output table : 'difficulte_Out'
								difficulte_Out_tmp.id_difficulte = row2.id_difficulte;
								difficulte_Out_tmp.id_etudiant = row2.id_etudiant;
								difficulte_Out_tmp.maitrise_metier_etudiant = row2.maitrise_metier_etudiant;
								difficulte_Out_tmp.difficulte_adaptation_methodes = row2.difficulte_adaptation_methodes;
								difficulte_Out_tmp.difficulte_taille_groupes = row2.difficulte_taille_groupes;
								difficulte_Out_tmp.interet_lecture_ecriture = row2.interet_lecture_ecriture;
								difficulte_Out_tmp.frequence_etude_lecons = row2.frequence_etude_lecons;
								difficulte_Out_tmp.attente_veille_examens = row2.attente_veille_examens;
								difficulte_Out_tmp.participation_examens_semestriels = row2.participation_examens_semestriels;
								difficulte_Out_tmp.passe_scolaire_difficile = row2.passe_scolaire_difficile;
								difficulte_Out_tmp.decrochage_anterieur_sec_prim = row2.decrochage_anterieur_sec_prim;
								difficulte_Out_tmp.difficultes_pedagogiques_specifiques = row2.difficultes_pedagogiques_specifiques;
								difficulte_Out = difficulte_Out_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_2 = false;

							tos_count_tMap_2++;

							/**
							 * [tMap_2 main ] stop
							 */

							/**
							 * [tMap_2 process_data_begin ] start
							 */

							currentComponent = "tMap_2";

							/**
							 * [tMap_2 process_data_begin ] stop
							 */
// Start of branch "difficulte_Out"
							if (difficulte_Out != null) {

								/**
								 * [tAdvancedHash_difficulte_Out main ] start
								 */

								currentComponent = "tAdvancedHash_difficulte_Out";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "difficulte_Out"

									);
								}

								difficulte_OutStruct difficulte_Out_HashRow = new difficulte_OutStruct();

								difficulte_Out_HashRow.id_difficulte = difficulte_Out.id_difficulte;

								difficulte_Out_HashRow.id_etudiant = difficulte_Out.id_etudiant;

								difficulte_Out_HashRow.maitrise_metier_etudiant = difficulte_Out.maitrise_metier_etudiant;

								difficulte_Out_HashRow.difficulte_adaptation_methodes = difficulte_Out.difficulte_adaptation_methodes;

								difficulte_Out_HashRow.difficulte_taille_groupes = difficulte_Out.difficulte_taille_groupes;

								difficulte_Out_HashRow.interet_lecture_ecriture = difficulte_Out.interet_lecture_ecriture;

								difficulte_Out_HashRow.frequence_etude_lecons = difficulte_Out.frequence_etude_lecons;

								difficulte_Out_HashRow.attente_veille_examens = difficulte_Out.attente_veille_examens;

								difficulte_Out_HashRow.participation_examens_semestriels = difficulte_Out.participation_examens_semestriels;

								difficulte_Out_HashRow.passe_scolaire_difficile = difficulte_Out.passe_scolaire_difficile;

								difficulte_Out_HashRow.decrochage_anterieur_sec_prim = difficulte_Out.decrochage_anterieur_sec_prim;

								difficulte_Out_HashRow.difficultes_pedagogiques_specifiques = difficulte_Out.difficultes_pedagogiques_specifiques;

								tHash_Lookup_difficulte_Out.put(difficulte_Out_HashRow);

								tos_count_tAdvancedHash_difficulte_Out++;

								/**
								 * [tAdvancedHash_difficulte_Out main ] stop
								 */

								/**
								 * [tAdvancedHash_difficulte_Out process_data_begin ] start
								 */

								currentComponent = "tAdvancedHash_difficulte_Out";

								/**
								 * [tAdvancedHash_difficulte_Out process_data_begin ] stop
								 */

								/**
								 * [tAdvancedHash_difficulte_Out process_data_end ] start
								 */

								currentComponent = "tAdvancedHash_difficulte_Out";

								/**
								 * [tAdvancedHash_difficulte_Out process_data_end ] stop
								 */

							} // End of branch "difficulte_Out"

							/**
							 * [tMap_2 process_data_end ] start
							 */

							currentComponent = "tMap_2";

							/**
							 * [tMap_2 process_data_end ] stop
							 */

						} // End of branch "row2"

						/**
						 * [tFileInputDelimited_2 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_2 end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

					}
				} finally {
					if (!((Object) ("C:/home/claude/education_bi_v2/dim_difficultes_academiques.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_2 != null) {
							fid_tFileInputDelimited_2.close();
						}
					}
					if (fid_tFileInputDelimited_2 != null) {
						globalMap.put("tFileInputDelimited_2_NB_LINE", fid_tFileInputDelimited_2.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_2", true);
				end_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_2 end ] stop
				 */

				/**
				 * [tMap_2 end ] start
				 */

				currentComponent = "tMap_2";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tMap_2", true);
				end_Hash.put("tMap_2", System.currentTimeMillis());

				/**
				 * [tMap_2 end ] stop
				 */

				/**
				 * [tAdvancedHash_difficulte_Out end ] start
				 */

				currentComponent = "tAdvancedHash_difficulte_Out";

				tHash_Lookup_difficulte_Out.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "difficulte_Out");
				}

				ok_Hash.put("tAdvancedHash_difficulte_Out", true);
				end_Hash.put("tAdvancedHash_difficulte_Out", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_difficulte_Out end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_2 finally ] start
				 */

				currentComponent = "tFileInputDelimited_2";

				/**
				 * [tFileInputDelimited_2 finally ] stop
				 */

				/**
				 * [tMap_2 finally ] start
				 */

				currentComponent = "tMap_2";

				/**
				 * [tMap_2 finally ] stop
				 */

				/**
				 * [tAdvancedHash_difficulte_Out finally ] start
				 */

				currentComponent = "tAdvancedHash_difficulte_Out";

				/**
				 * [tAdvancedHash_difficulte_Out finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 1);
	}

	public static class finance_OutStruct
			implements routines.system.IPersistableComparableLookupRow<finance_OutStruct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_13_Score_Risque = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int id_situation_eco;

		public int getId_situation_eco() {
			return this.id_situation_eco;
		}

		public Integer id_etudiant;

		public Integer getId_etudiant() {
			return this.id_etudiant;
		}

		public String mode_vie;

		public String getMode_vie() {
			return this.mode_vie;
		}

		public Integer a_emploi_remunere;

		public Integer getA_emploi_remunere() {
			return this.a_emploi_remunere;
		}

		public Integer heures_emploi_semaine;

		public Integer getHeures_emploi_semaine() {
			return this.heures_emploi_semaine;
		}

		public String type_emploi;

		public String getType_emploi() {
			return this.type_emploi;
		}

		public Integer beneficie_bourse;

		public Integer getBeneficie_bourse() {
			return this.beneficie_bourse;
		}

		public Integer montant_bourse_mensuel_fcfa;

		public Integer getMontant_bourse_mensuel_fcfa() {
			return this.montant_bourse_mensuel_fcfa;
		}

		public String source_financement_principale;

		public String getSource_financement_principale() {
			return this.source_financement_principale;
		}

		public Integer difficultes_financieres_recurrentes;

		public Integer getDifficultes_financieres_recurrentes() {
			return this.difficultes_financieres_recurrentes;
		}

		public Integer depenses_mensuelles_fcfa;

		public Integer getDepenses_mensuelles_fcfa() {
			return this.depenses_mensuelles_fcfa;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.id_etudiant == null) ? 0 : this.id_etudiant.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final finance_OutStruct other = (finance_OutStruct) obj;

			if (this.id_etudiant == null) {
				if (other.id_etudiant != null)
					return false;

			} else if (!this.id_etudiant.equals(other.id_etudiant))

				return false;

			return true;
		}

		public void copyDataTo(finance_OutStruct other) {

			other.id_situation_eco = this.id_situation_eco;
			other.id_etudiant = this.id_etudiant;
			other.mode_vie = this.mode_vie;
			other.a_emploi_remunere = this.a_emploi_remunere;
			other.heures_emploi_semaine = this.heures_emploi_semaine;
			other.type_emploi = this.type_emploi;
			other.beneficie_bourse = this.beneficie_bourse;
			other.montant_bourse_mensuel_fcfa = this.montant_bourse_mensuel_fcfa;
			other.source_financement_principale = this.source_financement_principale;
			other.difficultes_financieres_recurrentes = this.difficultes_financieres_recurrentes;
			other.depenses_mensuelles_fcfa = this.depenses_mensuelles_fcfa;

		}

		public void copyKeysDataTo(finance_OutStruct other) {

			other.id_etudiant = this.id_etudiant;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_etudiant = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_etudiant = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.id_etudiant, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.id_etudiant, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.id_situation_eco = dis.readInt();

				this.mode_vie = readString(dis, ois);

				this.a_emploi_remunere = readInteger(dis, ois);

				this.heures_emploi_semaine = readInteger(dis, ois);

				this.type_emploi = readString(dis, ois);

				this.beneficie_bourse = readInteger(dis, ois);

				this.montant_bourse_mensuel_fcfa = readInteger(dis, ois);

				this.source_financement_principale = readString(dis, ois);

				this.difficultes_financieres_recurrentes = readInteger(dis, ois);

				this.depenses_mensuelles_fcfa = readInteger(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.id_situation_eco = objectIn.readInt();

				this.mode_vie = readString(dis, objectIn);

				this.a_emploi_remunere = readInteger(dis, objectIn);

				this.heures_emploi_semaine = readInteger(dis, objectIn);

				this.type_emploi = readString(dis, objectIn);

				this.beneficie_bourse = readInteger(dis, objectIn);

				this.montant_bourse_mensuel_fcfa = readInteger(dis, objectIn);

				this.source_financement_principale = readString(dis, objectIn);

				this.difficultes_financieres_recurrentes = readInteger(dis, objectIn);

				this.depenses_mensuelles_fcfa = readInteger(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				dos.writeInt(this.id_situation_eco);

				writeString(this.mode_vie, dos, oos);

				writeInteger(this.a_emploi_remunere, dos, oos);

				writeInteger(this.heures_emploi_semaine, dos, oos);

				writeString(this.type_emploi, dos, oos);

				writeInteger(this.beneficie_bourse, dos, oos);

				writeInteger(this.montant_bourse_mensuel_fcfa, dos, oos);

				writeString(this.source_financement_principale, dos, oos);

				writeInteger(this.difficultes_financieres_recurrentes, dos, oos);

				writeInteger(this.depenses_mensuelles_fcfa, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				objectOut.writeInt(this.id_situation_eco);

				writeString(this.mode_vie, dos, objectOut);

				writeInteger(this.a_emploi_remunere, dos, objectOut);

				writeInteger(this.heures_emploi_semaine, dos, objectOut);

				writeString(this.type_emploi, dos, objectOut);

				writeInteger(this.beneficie_bourse, dos, objectOut);

				writeInteger(this.montant_bourse_mensuel_fcfa, dos, objectOut);

				writeString(this.source_financement_principale, dos, objectOut);

				writeInteger(this.difficultes_financieres_recurrentes, dos, objectOut);

				writeInteger(this.depenses_mensuelles_fcfa, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_situation_eco=" + String.valueOf(id_situation_eco));
			sb.append(",id_etudiant=" + String.valueOf(id_etudiant));
			sb.append(",mode_vie=" + mode_vie);
			sb.append(",a_emploi_remunere=" + String.valueOf(a_emploi_remunere));
			sb.append(",heures_emploi_semaine=" + String.valueOf(heures_emploi_semaine));
			sb.append(",type_emploi=" + type_emploi);
			sb.append(",beneficie_bourse=" + String.valueOf(beneficie_bourse));
			sb.append(",montant_bourse_mensuel_fcfa=" + String.valueOf(montant_bourse_mensuel_fcfa));
			sb.append(",source_financement_principale=" + source_financement_principale);
			sb.append(",difficultes_financieres_recurrentes=" + String.valueOf(difficultes_financieres_recurrentes));
			sb.append(",depenses_mensuelles_fcfa=" + String.valueOf(depenses_mensuelles_fcfa));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(finance_OutStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id_etudiant, other.id_etudiant);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_13_Score_Risque = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[0];

		public int id_situation_eco;

		public int getId_situation_eco() {
			return this.id_situation_eco;
		}

		public Integer id_etudiant;

		public Integer getId_etudiant() {
			return this.id_etudiant;
		}

		public String mode_vie;

		public String getMode_vie() {
			return this.mode_vie;
		}

		public Integer a_emploi_remunere;

		public Integer getA_emploi_remunere() {
			return this.a_emploi_remunere;
		}

		public Integer heures_emploi_semaine;

		public Integer getHeures_emploi_semaine() {
			return this.heures_emploi_semaine;
		}

		public String type_emploi;

		public String getType_emploi() {
			return this.type_emploi;
		}

		public Integer beneficie_bourse;

		public Integer getBeneficie_bourse() {
			return this.beneficie_bourse;
		}

		public Integer montant_bourse_mensuel_fcfa;

		public Integer getMontant_bourse_mensuel_fcfa() {
			return this.montant_bourse_mensuel_fcfa;
		}

		public String source_financement_principale;

		public String getSource_financement_principale() {
			return this.source_financement_principale;
		}

		public Integer difficultes_financieres_recurrentes;

		public Integer getDifficultes_financieres_recurrentes() {
			return this.difficultes_financieres_recurrentes;
		}

		public Integer depenses_mensuelles_fcfa;

		public Integer getDepenses_mensuelles_fcfa() {
			return this.depenses_mensuelles_fcfa;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_13_Score_Risque.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_13_Score_Risque.length == 0) {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_13_Score_Risque.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_13_Score_Risque.length == 0) {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_situation_eco = dis.readInt();

					this.id_etudiant = readInteger(dis);

					this.mode_vie = readString(dis);

					this.a_emploi_remunere = readInteger(dis);

					this.heures_emploi_semaine = readInteger(dis);

					this.type_emploi = readString(dis);

					this.beneficie_bourse = readInteger(dis);

					this.montant_bourse_mensuel_fcfa = readInteger(dis);

					this.source_financement_principale = readString(dis);

					this.difficultes_financieres_recurrentes = readInteger(dis);

					this.depenses_mensuelles_fcfa = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_situation_eco = dis.readInt();

					this.id_etudiant = readInteger(dis);

					this.mode_vie = readString(dis);

					this.a_emploi_remunere = readInteger(dis);

					this.heures_emploi_semaine = readInteger(dis);

					this.type_emploi = readString(dis);

					this.beneficie_bourse = readInteger(dis);

					this.montant_bourse_mensuel_fcfa = readInteger(dis);

					this.source_financement_principale = readString(dis);

					this.difficultes_financieres_recurrentes = readInteger(dis);

					this.depenses_mensuelles_fcfa = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.id_situation_eco);

				// Integer

				writeInteger(this.id_etudiant, dos);

				// String

				writeString(this.mode_vie, dos);

				// Integer

				writeInteger(this.a_emploi_remunere, dos);

				// Integer

				writeInteger(this.heures_emploi_semaine, dos);

				// String

				writeString(this.type_emploi, dos);

				// Integer

				writeInteger(this.beneficie_bourse, dos);

				// Integer

				writeInteger(this.montant_bourse_mensuel_fcfa, dos);

				// String

				writeString(this.source_financement_principale, dos);

				// Integer

				writeInteger(this.difficultes_financieres_recurrentes, dos);

				// Integer

				writeInteger(this.depenses_mensuelles_fcfa, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.id_situation_eco);

				// Integer

				writeInteger(this.id_etudiant, dos);

				// String

				writeString(this.mode_vie, dos);

				// Integer

				writeInteger(this.a_emploi_remunere, dos);

				// Integer

				writeInteger(this.heures_emploi_semaine, dos);

				// String

				writeString(this.type_emploi, dos);

				// Integer

				writeInteger(this.beneficie_bourse, dos);

				// Integer

				writeInteger(this.montant_bourse_mensuel_fcfa, dos);

				// String

				writeString(this.source_financement_principale, dos);

				// Integer

				writeInteger(this.difficultes_financieres_recurrentes, dos);

				// Integer

				writeInteger(this.depenses_mensuelles_fcfa, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_situation_eco=" + String.valueOf(id_situation_eco));
			sb.append(",id_etudiant=" + String.valueOf(id_etudiant));
			sb.append(",mode_vie=" + mode_vie);
			sb.append(",a_emploi_remunere=" + String.valueOf(a_emploi_remunere));
			sb.append(",heures_emploi_semaine=" + String.valueOf(heures_emploi_semaine));
			sb.append(",type_emploi=" + type_emploi);
			sb.append(",beneficie_bourse=" + String.valueOf(beneficie_bourse));
			sb.append(",montant_bourse_mensuel_fcfa=" + String.valueOf(montant_bourse_mensuel_fcfa));
			sb.append(",source_financement_principale=" + source_financement_principale);
			sb.append(",difficultes_financieres_recurrentes=" + String.valueOf(difficultes_financieres_recurrentes));
			sb.append(",depenses_mensuelles_fcfa=" + String.valueOf(depenses_mensuelles_fcfa));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row3Struct row3 = new row3Struct();
				finance_OutStruct finance_Out = new finance_OutStruct();

				/**
				 * [tAdvancedHash_finance_Out begin ] start
				 */

				ok_Hash.put("tAdvancedHash_finance_Out", false);
				start_Hash.put("tAdvancedHash_finance_Out", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_finance_Out";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "finance_Out");
				}

				int tos_count_tAdvancedHash_finance_Out = 0;

				// connection name:finance_Out
				// source node:tMap_3 - inputs:(row3) outputs:(finance_Out,finance_Out) | target
				// node:tAdvancedHash_finance_Out - inputs:(finance_Out) outputs:()
				// linked node: tMap_4 -
				// inputs:(sociale_Out,difficulte_Out,finance_Out,etudiant_Out)
				// outputs:(score_risque_Output)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_finance_Out = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<finance_OutStruct> tHash_Lookup_finance_Out = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<finance_OutStruct>getLookup(matchingModeEnum_finance_Out);

				globalMap.put("tHash_Lookup_finance_Out", tHash_Lookup_finance_Out);

				/**
				 * [tAdvancedHash_finance_Out begin ] stop
				 */

				/**
				 * [tMap_3 begin ] start
				 */

				ok_Hash.put("tMap_3", false);
				start_Hash.put("tMap_3", System.currentTimeMillis());

				currentComponent = "tMap_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tMap_3 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_3__Struct {
				}
				Var__tMap_3__Struct Var__tMap_3 = new Var__tMap_3__Struct();
// ###############################

// ###############################
// # Outputs initialization
				finance_OutStruct finance_Out_tmp = new finance_OutStruct();
// ###############################

				/**
				 * [tMap_3 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_3 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_3", false);
				start_Hash.put("tFileInputDelimited_3", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_3";

				int tos_count_tFileInputDelimited_3 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_3 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_3 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_3 = null;
				int limit_tFileInputDelimited_3 = -1;
				try {

					Object filename_tFileInputDelimited_3 = "C:/home/claude/education_bi_v2/dim_situation_economique.csv";
					if (filename_tFileInputDelimited_3 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_3 = 0, random_value_tFileInputDelimited_3 = -1;
						if (footer_value_tFileInputDelimited_3 > 0 || random_value_tFileInputDelimited_3 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_3 = new org.talend.fileprocess.FileInputDelimited(
								"C:/home/claude/education_bi_v2/dim_situation_economique.csv", "UTF-8", ",", "\n", true,
								1, 0, limit_tFileInputDelimited_3, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_3 != null && fid_tFileInputDelimited_3.nextRecord()) {
						rowstate_tFileInputDelimited_3.reset();

						row3 = null;

						boolean whetherReject_tFileInputDelimited_3 = false;
						row3 = new row3Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_3 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_3 = 0;

							temp = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									row3.id_situation_eco = ParserUtils.parseTo_int(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
											ex_tFileInputDelimited_3.getMessage());
									rowstate_tFileInputDelimited_3.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"id_situation_eco", "row3", temp, ex_tFileInputDelimited_3),
											ex_tFileInputDelimited_3));
								}

							} else {

								rowstate_tFileInputDelimited_3.setException(new RuntimeException(
										"Value is empty for column : 'id_situation_eco' in 'row3' connection, value is invalid or this column should be nullable or have a default value."));

							}

							columnIndexWithD_tFileInputDelimited_3 = 1;

							temp = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									row3.id_etudiant = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
											ex_tFileInputDelimited_3.getMessage());
									rowstate_tFileInputDelimited_3.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"id_etudiant", "row3", temp, ex_tFileInputDelimited_3),
											ex_tFileInputDelimited_3));
								}

							} else {

								row3.id_etudiant = null;

							}

							columnIndexWithD_tFileInputDelimited_3 = 2;

							row3.mode_vie = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 3;

							temp = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									row3.a_emploi_remunere = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
											ex_tFileInputDelimited_3.getMessage());
									rowstate_tFileInputDelimited_3.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"a_emploi_remunere", "row3", temp, ex_tFileInputDelimited_3),
											ex_tFileInputDelimited_3));
								}

							} else {

								row3.a_emploi_remunere = null;

							}

							columnIndexWithD_tFileInputDelimited_3 = 4;

							temp = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									row3.heures_emploi_semaine = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
											ex_tFileInputDelimited_3.getMessage());
									rowstate_tFileInputDelimited_3.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"heures_emploi_semaine", "row3", temp, ex_tFileInputDelimited_3),
											ex_tFileInputDelimited_3));
								}

							} else {

								row3.heures_emploi_semaine = null;

							}

							columnIndexWithD_tFileInputDelimited_3 = 5;

							row3.type_emploi = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 6;

							temp = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									row3.beneficie_bourse = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
											ex_tFileInputDelimited_3.getMessage());
									rowstate_tFileInputDelimited_3.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"beneficie_bourse", "row3", temp, ex_tFileInputDelimited_3),
											ex_tFileInputDelimited_3));
								}

							} else {

								row3.beneficie_bourse = null;

							}

							columnIndexWithD_tFileInputDelimited_3 = 7;

							temp = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									row3.montant_bourse_mensuel_fcfa = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
											ex_tFileInputDelimited_3.getMessage());
									rowstate_tFileInputDelimited_3.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"montant_bourse_mensuel_fcfa", "row3", temp, ex_tFileInputDelimited_3),
											ex_tFileInputDelimited_3));
								}

							} else {

								row3.montant_bourse_mensuel_fcfa = null;

							}

							columnIndexWithD_tFileInputDelimited_3 = 8;

							row3.source_financement_principale = fid_tFileInputDelimited_3
									.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 9;

							temp = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									row3.difficultes_financieres_recurrentes = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
											ex_tFileInputDelimited_3.getMessage());
									rowstate_tFileInputDelimited_3.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"difficultes_financieres_recurrentes", "row3", temp,
											ex_tFileInputDelimited_3), ex_tFileInputDelimited_3));
								}

							} else {

								row3.difficultes_financieres_recurrentes = null;

							}

							columnIndexWithD_tFileInputDelimited_3 = 10;

							temp = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									row3.depenses_mensuelles_fcfa = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
											ex_tFileInputDelimited_3.getMessage());
									rowstate_tFileInputDelimited_3.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"depenses_mensuelles_fcfa", "row3", temp, ex_tFileInputDelimited_3),
											ex_tFileInputDelimited_3));
								}

							} else {

								row3.depenses_mensuelles_fcfa = null;

							}

							if (rowstate_tFileInputDelimited_3.getException() != null) {
								throw rowstate_tFileInputDelimited_3.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_3 = true;

							System.err.println(e.getMessage());
							row3 = null;

						}

						/**
						 * [tFileInputDelimited_3 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_3 main ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						tos_count_tFileInputDelimited_3++;

						/**
						 * [tFileInputDelimited_3 main ] stop
						 */

						/**
						 * [tFileInputDelimited_3 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						/**
						 * [tFileInputDelimited_3 process_data_begin ] stop
						 */
// Start of branch "row3"
						if (row3 != null) {

							/**
							 * [tMap_3 main ] start
							 */

							currentComponent = "tMap_3";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row3"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_3 = false;
							boolean mainRowRejected_tMap_3 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_3__Struct Var = Var__tMap_3;// ###############################
								// ###############################
								// # Output tables

								finance_Out = null;

// # Output table : 'finance_Out'
								finance_Out_tmp.id_situation_eco = row3.id_situation_eco;
								finance_Out_tmp.id_etudiant = row3.id_etudiant;
								finance_Out_tmp.mode_vie = row3.mode_vie;
								finance_Out_tmp.a_emploi_remunere = row3.a_emploi_remunere;
								finance_Out_tmp.heures_emploi_semaine = row3.heures_emploi_semaine;
								finance_Out_tmp.type_emploi = row3.type_emploi;
								finance_Out_tmp.beneficie_bourse = row3.beneficie_bourse;
								finance_Out_tmp.montant_bourse_mensuel_fcfa = row3.montant_bourse_mensuel_fcfa;
								finance_Out_tmp.source_financement_principale = row3.source_financement_principale;
								finance_Out_tmp.difficultes_financieres_recurrentes = row3.difficultes_financieres_recurrentes;
								finance_Out_tmp.depenses_mensuelles_fcfa = row3.depenses_mensuelles_fcfa;
								finance_Out = finance_Out_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_3 = false;

							tos_count_tMap_3++;

							/**
							 * [tMap_3 main ] stop
							 */

							/**
							 * [tMap_3 process_data_begin ] start
							 */

							currentComponent = "tMap_3";

							/**
							 * [tMap_3 process_data_begin ] stop
							 */
// Start of branch "finance_Out"
							if (finance_Out != null) {

								/**
								 * [tAdvancedHash_finance_Out main ] start
								 */

								currentComponent = "tAdvancedHash_finance_Out";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "finance_Out"

									);
								}

								finance_OutStruct finance_Out_HashRow = new finance_OutStruct();

								finance_Out_HashRow.id_situation_eco = finance_Out.id_situation_eco;

								finance_Out_HashRow.id_etudiant = finance_Out.id_etudiant;

								finance_Out_HashRow.mode_vie = finance_Out.mode_vie;

								finance_Out_HashRow.a_emploi_remunere = finance_Out.a_emploi_remunere;

								finance_Out_HashRow.heures_emploi_semaine = finance_Out.heures_emploi_semaine;

								finance_Out_HashRow.type_emploi = finance_Out.type_emploi;

								finance_Out_HashRow.beneficie_bourse = finance_Out.beneficie_bourse;

								finance_Out_HashRow.montant_bourse_mensuel_fcfa = finance_Out.montant_bourse_mensuel_fcfa;

								finance_Out_HashRow.source_financement_principale = finance_Out.source_financement_principale;

								finance_Out_HashRow.difficultes_financieres_recurrentes = finance_Out.difficultes_financieres_recurrentes;

								finance_Out_HashRow.depenses_mensuelles_fcfa = finance_Out.depenses_mensuelles_fcfa;

								tHash_Lookup_finance_Out.put(finance_Out_HashRow);

								tos_count_tAdvancedHash_finance_Out++;

								/**
								 * [tAdvancedHash_finance_Out main ] stop
								 */

								/**
								 * [tAdvancedHash_finance_Out process_data_begin ] start
								 */

								currentComponent = "tAdvancedHash_finance_Out";

								/**
								 * [tAdvancedHash_finance_Out process_data_begin ] stop
								 */

								/**
								 * [tAdvancedHash_finance_Out process_data_end ] start
								 */

								currentComponent = "tAdvancedHash_finance_Out";

								/**
								 * [tAdvancedHash_finance_Out process_data_end ] stop
								 */

							} // End of branch "finance_Out"

							/**
							 * [tMap_3 process_data_end ] start
							 */

							currentComponent = "tMap_3";

							/**
							 * [tMap_3 process_data_end ] stop
							 */

						} // End of branch "row3"

						/**
						 * [tFileInputDelimited_3 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						/**
						 * [tFileInputDelimited_3 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_3 end ] start
						 */

						currentComponent = "tFileInputDelimited_3";

					}
				} finally {
					if (!((Object) ("C:/home/claude/education_bi_v2/dim_situation_economique.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_3 != null) {
							fid_tFileInputDelimited_3.close();
						}
					}
					if (fid_tFileInputDelimited_3 != null) {
						globalMap.put("tFileInputDelimited_3_NB_LINE", fid_tFileInputDelimited_3.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_3", true);
				end_Hash.put("tFileInputDelimited_3", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_3 end ] stop
				 */

				/**
				 * [tMap_3 end ] start
				 */

				currentComponent = "tMap_3";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tMap_3", true);
				end_Hash.put("tMap_3", System.currentTimeMillis());

				/**
				 * [tMap_3 end ] stop
				 */

				/**
				 * [tAdvancedHash_finance_Out end ] start
				 */

				currentComponent = "tAdvancedHash_finance_Out";

				tHash_Lookup_finance_Out.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "finance_Out");
				}

				ok_Hash.put("tAdvancedHash_finance_Out", true);
				end_Hash.put("tAdvancedHash_finance_Out", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_finance_Out end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_3 finally ] start
				 */

				currentComponent = "tFileInputDelimited_3";

				/**
				 * [tFileInputDelimited_3 finally ] stop
				 */

				/**
				 * [tMap_3 finally ] start
				 */

				currentComponent = "tMap_3";

				/**
				 * [tMap_3 finally ] stop
				 */

				/**
				 * [tAdvancedHash_finance_Out finally ] start
				 */

				currentComponent = "tAdvancedHash_finance_Out";

				/**
				 * [tAdvancedHash_finance_Out finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 1);
	}

	public static class score_risque_OutputStruct
			implements routines.system.IPersistableRow<score_risque_OutputStruct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_13_Score_Risque = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int id_etudiant;

		public int getId_etudiant() {
			return this.id_etudiant;
		}

		public Integer isolement_social;

		public Integer getIsolement_social() {
			return this.isolement_social;
		}

		public Integer victime_harcelement;

		public Integer getVictime_harcelement() {
			return this.victime_harcelement;
		}

		public Integer difficultes_financieres_recurrentes;

		public Integer getDifficultes_financieres_recurrentes() {
			return this.difficultes_financieres_recurrentes;
		}

		public String estime_de_soi;

		public String getEstime_de_soi() {
			return this.estime_de_soi;
		}

		public String frequence_etude_lecons;

		public String getFrequence_etude_lecons() {
			return this.frequence_etude_lecons;
		}

		public Integer score_risque;

		public Integer getScore_risque() {
			return this.score_risque;
		}

		public String niveau_risque;

		public String getNiveau_risque() {
			return this.niveau_risque;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.id_etudiant;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final score_risque_OutputStruct other = (score_risque_OutputStruct) obj;

			if (this.id_etudiant != other.id_etudiant)
				return false;

			return true;
		}

		public void copyDataTo(score_risque_OutputStruct other) {

			other.id_etudiant = this.id_etudiant;
			other.isolement_social = this.isolement_social;
			other.victime_harcelement = this.victime_harcelement;
			other.difficultes_financieres_recurrentes = this.difficultes_financieres_recurrentes;
			other.estime_de_soi = this.estime_de_soi;
			other.frequence_etude_lecons = this.frequence_etude_lecons;
			other.score_risque = this.score_risque;
			other.niveau_risque = this.niveau_risque;

		}

		public void copyKeysDataTo(score_risque_OutputStruct other) {

			other.id_etudiant = this.id_etudiant;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_13_Score_Risque.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_13_Score_Risque.length == 0) {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_13_Score_Risque.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_13_Score_Risque.length == 0) {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_etudiant = dis.readInt();

					this.isolement_social = readInteger(dis);

					this.victime_harcelement = readInteger(dis);

					this.difficultes_financieres_recurrentes = readInteger(dis);

					this.estime_de_soi = readString(dis);

					this.frequence_etude_lecons = readString(dis);

					this.score_risque = readInteger(dis);

					this.niveau_risque = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_etudiant = dis.readInt();

					this.isolement_social = readInteger(dis);

					this.victime_harcelement = readInteger(dis);

					this.difficultes_financieres_recurrentes = readInteger(dis);

					this.estime_de_soi = readString(dis);

					this.frequence_etude_lecons = readString(dis);

					this.score_risque = readInteger(dis);

					this.niveau_risque = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.id_etudiant);

				// Integer

				writeInteger(this.isolement_social, dos);

				// Integer

				writeInteger(this.victime_harcelement, dos);

				// Integer

				writeInteger(this.difficultes_financieres_recurrentes, dos);

				// String

				writeString(this.estime_de_soi, dos);

				// String

				writeString(this.frequence_etude_lecons, dos);

				// Integer

				writeInteger(this.score_risque, dos);

				// String

				writeString(this.niveau_risque, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.id_etudiant);

				// Integer

				writeInteger(this.isolement_social, dos);

				// Integer

				writeInteger(this.victime_harcelement, dos);

				// Integer

				writeInteger(this.difficultes_financieres_recurrentes, dos);

				// String

				writeString(this.estime_de_soi, dos);

				// String

				writeString(this.frequence_etude_lecons, dos);

				// Integer

				writeInteger(this.score_risque, dos);

				// String

				writeString(this.niveau_risque, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_etudiant=" + String.valueOf(id_etudiant));
			sb.append(",isolement_social=" + String.valueOf(isolement_social));
			sb.append(",victime_harcelement=" + String.valueOf(victime_harcelement));
			sb.append(",difficultes_financieres_recurrentes=" + String.valueOf(difficultes_financieres_recurrentes));
			sb.append(",estime_de_soi=" + estime_de_soi);
			sb.append(",frequence_etude_lecons=" + frequence_etude_lecons);
			sb.append(",score_risque=" + String.valueOf(score_risque));
			sb.append(",niveau_risque=" + niveau_risque);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(score_risque_OutputStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id_etudiant, other.id_etudiant);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class etudiant_OutStruct implements routines.system.IPersistableRow<etudiant_OutStruct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_13_Score_Risque = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[0];

		public int id_etudiant;

		public int getId_etudiant() {
			return this.id_etudiant;
		}

		public String matricule;

		public String getMatricule() {
			return this.matricule;
		}

		public String email_etudiant;

		public String getEmail_etudiant() {
			return this.email_etudiant;
		}

		public String nom;

		public String getNom() {
			return this.nom;
		}

		public String prenom;

		public String getPrenom() {
			return this.prenom;
		}

		public Character genre;

		public Character getGenre() {
			return this.genre;
		}

		public java.util.Date date_naissance;

		public java.util.Date getDate_naissance() {
			return this.date_naissance;
		}

		public String niveau_socio_economique;

		public String getNiveau_socio_economique() {
			return this.niveau_socio_economique;
		}

		public Integer id_filiere;

		public Integer getId_filiere() {
			return this.id_filiere;
		}

		public Integer annee_entree;

		public Integer getAnnee_entree() {
			return this.annee_entree;
		}

		public String ville_origine;

		public String getVille_origine() {
			return this.ville_origine;
		}

		public String situation_matrimoniale;

		public String getSituation_matrimoniale() {
			return this.situation_matrimoniale;
		}

		public Integer nb_enfants_a_charge;

		public Integer getNb_enfants_a_charge() {
			return this.nb_enfants_a_charge;
		}

		public String estime_de_soi;

		public String getEstime_de_soi() {
			return this.estime_de_soi;
		}

		public String trait_personnalite;

		public String getTrait_personnalite() {
			return this.trait_personnalite;
		}

		public String mention_baccalaureat;

		public String getMention_baccalaureat() {
			return this.mention_baccalaureat;
		}

		public String serie_terminale;

		public String getSerie_terminale() {
			return this.serie_terminale;
		}

		public Integer decrochage_anterieur;

		public Integer getDecrochage_anterieur() {
			return this.decrochage_anterieur;
		}

		public String frequence_etude_lecons;

		public String getFrequence_etude_lecons() {
			return this.frequence_etude_lecons;
		}

		public String interet_lecture_ecriture;

		public String getInteret_lecture_ecriture() {
			return this.interet_lecture_ecriture;
		}

		public String adresse_email;

		public String getAdresse_email() {
			return this.adresse_email;
		}

		public String telephone;

		public String getTelephone() {
			return this.telephone;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_13_Score_Risque.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_13_Score_Risque.length == 0) {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_13_Score_Risque.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_13_Score_Risque.length == 0) {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_etudiant = dis.readInt();

					this.matricule = readString(dis);

					this.email_etudiant = readString(dis);

					this.nom = readString(dis);

					this.prenom = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.genre = null;
					} else {
						this.genre = dis.readChar();
					}

					this.date_naissance = readDate(dis);

					this.niveau_socio_economique = readString(dis);

					this.id_filiere = readInteger(dis);

					this.annee_entree = readInteger(dis);

					this.ville_origine = readString(dis);

					this.situation_matrimoniale = readString(dis);

					this.nb_enfants_a_charge = readInteger(dis);

					this.estime_de_soi = readString(dis);

					this.trait_personnalite = readString(dis);

					this.mention_baccalaureat = readString(dis);

					this.serie_terminale = readString(dis);

					this.decrochage_anterieur = readInteger(dis);

					this.frequence_etude_lecons = readString(dis);

					this.interet_lecture_ecriture = readString(dis);

					this.adresse_email = readString(dis);

					this.telephone = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_etudiant = dis.readInt();

					this.matricule = readString(dis);

					this.email_etudiant = readString(dis);

					this.nom = readString(dis);

					this.prenom = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.genre = null;
					} else {
						this.genre = dis.readChar();
					}

					this.date_naissance = readDate(dis);

					this.niveau_socio_economique = readString(dis);

					this.id_filiere = readInteger(dis);

					this.annee_entree = readInteger(dis);

					this.ville_origine = readString(dis);

					this.situation_matrimoniale = readString(dis);

					this.nb_enfants_a_charge = readInteger(dis);

					this.estime_de_soi = readString(dis);

					this.trait_personnalite = readString(dis);

					this.mention_baccalaureat = readString(dis);

					this.serie_terminale = readString(dis);

					this.decrochage_anterieur = readInteger(dis);

					this.frequence_etude_lecons = readString(dis);

					this.interet_lecture_ecriture = readString(dis);

					this.adresse_email = readString(dis);

					this.telephone = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.id_etudiant);

				// String

				writeString(this.matricule, dos);

				// String

				writeString(this.email_etudiant, dos);

				// String

				writeString(this.nom, dos);

				// String

				writeString(this.prenom, dos);

				// Character

				if (this.genre == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.genre);
				}

				// java.util.Date

				writeDate(this.date_naissance, dos);

				// String

				writeString(this.niveau_socio_economique, dos);

				// Integer

				writeInteger(this.id_filiere, dos);

				// Integer

				writeInteger(this.annee_entree, dos);

				// String

				writeString(this.ville_origine, dos);

				// String

				writeString(this.situation_matrimoniale, dos);

				// Integer

				writeInteger(this.nb_enfants_a_charge, dos);

				// String

				writeString(this.estime_de_soi, dos);

				// String

				writeString(this.trait_personnalite, dos);

				// String

				writeString(this.mention_baccalaureat, dos);

				// String

				writeString(this.serie_terminale, dos);

				// Integer

				writeInteger(this.decrochage_anterieur, dos);

				// String

				writeString(this.frequence_etude_lecons, dos);

				// String

				writeString(this.interet_lecture_ecriture, dos);

				// String

				writeString(this.adresse_email, dos);

				// String

				writeString(this.telephone, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.id_etudiant);

				// String

				writeString(this.matricule, dos);

				// String

				writeString(this.email_etudiant, dos);

				// String

				writeString(this.nom, dos);

				// String

				writeString(this.prenom, dos);

				// Character

				if (this.genre == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.genre);
				}

				// java.util.Date

				writeDate(this.date_naissance, dos);

				// String

				writeString(this.niveau_socio_economique, dos);

				// Integer

				writeInteger(this.id_filiere, dos);

				// Integer

				writeInteger(this.annee_entree, dos);

				// String

				writeString(this.ville_origine, dos);

				// String

				writeString(this.situation_matrimoniale, dos);

				// Integer

				writeInteger(this.nb_enfants_a_charge, dos);

				// String

				writeString(this.estime_de_soi, dos);

				// String

				writeString(this.trait_personnalite, dos);

				// String

				writeString(this.mention_baccalaureat, dos);

				// String

				writeString(this.serie_terminale, dos);

				// Integer

				writeInteger(this.decrochage_anterieur, dos);

				// String

				writeString(this.frequence_etude_lecons, dos);

				// String

				writeString(this.interet_lecture_ecriture, dos);

				// String

				writeString(this.adresse_email, dos);

				// String

				writeString(this.telephone, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_etudiant=" + String.valueOf(id_etudiant));
			sb.append(",matricule=" + matricule);
			sb.append(",email_etudiant=" + email_etudiant);
			sb.append(",nom=" + nom);
			sb.append(",prenom=" + prenom);
			sb.append(",genre=" + String.valueOf(genre));
			sb.append(",date_naissance=" + String.valueOf(date_naissance));
			sb.append(",niveau_socio_economique=" + niveau_socio_economique);
			sb.append(",id_filiere=" + String.valueOf(id_filiere));
			sb.append(",annee_entree=" + String.valueOf(annee_entree));
			sb.append(",ville_origine=" + ville_origine);
			sb.append(",situation_matrimoniale=" + situation_matrimoniale);
			sb.append(",nb_enfants_a_charge=" + String.valueOf(nb_enfants_a_charge));
			sb.append(",estime_de_soi=" + estime_de_soi);
			sb.append(",trait_personnalite=" + trait_personnalite);
			sb.append(",mention_baccalaureat=" + mention_baccalaureat);
			sb.append(",serie_terminale=" + serie_terminale);
			sb.append(",decrochage_anterieur=" + String.valueOf(decrochage_anterieur));
			sb.append(",frequence_etude_lecons=" + frequence_etude_lecons);
			sb.append(",interet_lecture_ecriture=" + interet_lecture_ecriture);
			sb.append(",adresse_email=" + adresse_email);
			sb.append(",telephone=" + telephone);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(etudiant_OutStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row4Struct implements routines.system.IPersistableRow<row4Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_13_Score_Risque = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[0];

		public int id_etudiant;

		public int getId_etudiant() {
			return this.id_etudiant;
		}

		public String matricule;

		public String getMatricule() {
			return this.matricule;
		}

		public String email_etudiant;

		public String getEmail_etudiant() {
			return this.email_etudiant;
		}

		public String nom;

		public String getNom() {
			return this.nom;
		}

		public String prenom;

		public String getPrenom() {
			return this.prenom;
		}

		public Character genre;

		public Character getGenre() {
			return this.genre;
		}

		public java.util.Date date_naissance;

		public java.util.Date getDate_naissance() {
			return this.date_naissance;
		}

		public String niveau_socio_economique;

		public String getNiveau_socio_economique() {
			return this.niveau_socio_economique;
		}

		public Integer id_filiere;

		public Integer getId_filiere() {
			return this.id_filiere;
		}

		public Integer annee_entree;

		public Integer getAnnee_entree() {
			return this.annee_entree;
		}

		public String ville_origine;

		public String getVille_origine() {
			return this.ville_origine;
		}

		public String situation_matrimoniale;

		public String getSituation_matrimoniale() {
			return this.situation_matrimoniale;
		}

		public Integer nb_enfants_a_charge;

		public Integer getNb_enfants_a_charge() {
			return this.nb_enfants_a_charge;
		}

		public String estime_de_soi;

		public String getEstime_de_soi() {
			return this.estime_de_soi;
		}

		public String trait_personnalite;

		public String getTrait_personnalite() {
			return this.trait_personnalite;
		}

		public String mention_baccalaureat;

		public String getMention_baccalaureat() {
			return this.mention_baccalaureat;
		}

		public String serie_terminale;

		public String getSerie_terminale() {
			return this.serie_terminale;
		}

		public Integer decrochage_anterieur;

		public Integer getDecrochage_anterieur() {
			return this.decrochage_anterieur;
		}

		public String frequence_etude_lecons;

		public String getFrequence_etude_lecons() {
			return this.frequence_etude_lecons;
		}

		public String interet_lecture_ecriture;

		public String getInteret_lecture_ecriture() {
			return this.interet_lecture_ecriture;
		}

		public String adresse_email;

		public String getAdresse_email() {
			return this.adresse_email;
		}

		public String telephone;

		public String getTelephone() {
			return this.telephone;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_13_Score_Risque.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_13_Score_Risque.length == 0) {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_13_Score_Risque.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_13_Score_Risque.length == 0) {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_etudiant = dis.readInt();

					this.matricule = readString(dis);

					this.email_etudiant = readString(dis);

					this.nom = readString(dis);

					this.prenom = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.genre = null;
					} else {
						this.genre = dis.readChar();
					}

					this.date_naissance = readDate(dis);

					this.niveau_socio_economique = readString(dis);

					this.id_filiere = readInteger(dis);

					this.annee_entree = readInteger(dis);

					this.ville_origine = readString(dis);

					this.situation_matrimoniale = readString(dis);

					this.nb_enfants_a_charge = readInteger(dis);

					this.estime_de_soi = readString(dis);

					this.trait_personnalite = readString(dis);

					this.mention_baccalaureat = readString(dis);

					this.serie_terminale = readString(dis);

					this.decrochage_anterieur = readInteger(dis);

					this.frequence_etude_lecons = readString(dis);

					this.interet_lecture_ecriture = readString(dis);

					this.adresse_email = readString(dis);

					this.telephone = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_etudiant = dis.readInt();

					this.matricule = readString(dis);

					this.email_etudiant = readString(dis);

					this.nom = readString(dis);

					this.prenom = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.genre = null;
					} else {
						this.genre = dis.readChar();
					}

					this.date_naissance = readDate(dis);

					this.niveau_socio_economique = readString(dis);

					this.id_filiere = readInteger(dis);

					this.annee_entree = readInteger(dis);

					this.ville_origine = readString(dis);

					this.situation_matrimoniale = readString(dis);

					this.nb_enfants_a_charge = readInteger(dis);

					this.estime_de_soi = readString(dis);

					this.trait_personnalite = readString(dis);

					this.mention_baccalaureat = readString(dis);

					this.serie_terminale = readString(dis);

					this.decrochage_anterieur = readInteger(dis);

					this.frequence_etude_lecons = readString(dis);

					this.interet_lecture_ecriture = readString(dis);

					this.adresse_email = readString(dis);

					this.telephone = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.id_etudiant);

				// String

				writeString(this.matricule, dos);

				// String

				writeString(this.email_etudiant, dos);

				// String

				writeString(this.nom, dos);

				// String

				writeString(this.prenom, dos);

				// Character

				if (this.genre == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.genre);
				}

				// java.util.Date

				writeDate(this.date_naissance, dos);

				// String

				writeString(this.niveau_socio_economique, dos);

				// Integer

				writeInteger(this.id_filiere, dos);

				// Integer

				writeInteger(this.annee_entree, dos);

				// String

				writeString(this.ville_origine, dos);

				// String

				writeString(this.situation_matrimoniale, dos);

				// Integer

				writeInteger(this.nb_enfants_a_charge, dos);

				// String

				writeString(this.estime_de_soi, dos);

				// String

				writeString(this.trait_personnalite, dos);

				// String

				writeString(this.mention_baccalaureat, dos);

				// String

				writeString(this.serie_terminale, dos);

				// Integer

				writeInteger(this.decrochage_anterieur, dos);

				// String

				writeString(this.frequence_etude_lecons, dos);

				// String

				writeString(this.interet_lecture_ecriture, dos);

				// String

				writeString(this.adresse_email, dos);

				// String

				writeString(this.telephone, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.id_etudiant);

				// String

				writeString(this.matricule, dos);

				// String

				writeString(this.email_etudiant, dos);

				// String

				writeString(this.nom, dos);

				// String

				writeString(this.prenom, dos);

				// Character

				if (this.genre == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.genre);
				}

				// java.util.Date

				writeDate(this.date_naissance, dos);

				// String

				writeString(this.niveau_socio_economique, dos);

				// Integer

				writeInteger(this.id_filiere, dos);

				// Integer

				writeInteger(this.annee_entree, dos);

				// String

				writeString(this.ville_origine, dos);

				// String

				writeString(this.situation_matrimoniale, dos);

				// Integer

				writeInteger(this.nb_enfants_a_charge, dos);

				// String

				writeString(this.estime_de_soi, dos);

				// String

				writeString(this.trait_personnalite, dos);

				// String

				writeString(this.mention_baccalaureat, dos);

				// String

				writeString(this.serie_terminale, dos);

				// Integer

				writeInteger(this.decrochage_anterieur, dos);

				// String

				writeString(this.frequence_etude_lecons, dos);

				// String

				writeString(this.interet_lecture_ecriture, dos);

				// String

				writeString(this.adresse_email, dos);

				// String

				writeString(this.telephone, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_etudiant=" + String.valueOf(id_etudiant));
			sb.append(",matricule=" + matricule);
			sb.append(",email_etudiant=" + email_etudiant);
			sb.append(",nom=" + nom);
			sb.append(",prenom=" + prenom);
			sb.append(",genre=" + String.valueOf(genre));
			sb.append(",date_naissance=" + String.valueOf(date_naissance));
			sb.append(",niveau_socio_economique=" + niveau_socio_economique);
			sb.append(",id_filiere=" + String.valueOf(id_filiere));
			sb.append(",annee_entree=" + String.valueOf(annee_entree));
			sb.append(",ville_origine=" + ville_origine);
			sb.append(",situation_matrimoniale=" + situation_matrimoniale);
			sb.append(",nb_enfants_a_charge=" + String.valueOf(nb_enfants_a_charge));
			sb.append(",estime_de_soi=" + estime_de_soi);
			sb.append(",trait_personnalite=" + trait_personnalite);
			sb.append(",mention_baccalaureat=" + mention_baccalaureat);
			sb.append(",serie_terminale=" + serie_terminale);
			sb.append(",decrochage_anterieur=" + String.valueOf(decrochage_anterieur));
			sb.append(",frequence_etude_lecons=" + frequence_etude_lecons);
			sb.append(",interet_lecture_ecriture=" + interet_lecture_ecriture);
			sb.append(",adresse_email=" + adresse_email);
			sb.append(",telephone=" + telephone);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputDelimited_4Struct
			implements routines.system.IPersistableRow<after_tFileInputDelimited_4Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_13_Score_Risque = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int id_etudiant;

		public int getId_etudiant() {
			return this.id_etudiant;
		}

		public String matricule;

		public String getMatricule() {
			return this.matricule;
		}

		public String email_etudiant;

		public String getEmail_etudiant() {
			return this.email_etudiant;
		}

		public String nom;

		public String getNom() {
			return this.nom;
		}

		public String prenom;

		public String getPrenom() {
			return this.prenom;
		}

		public Character genre;

		public Character getGenre() {
			return this.genre;
		}

		public java.util.Date date_naissance;

		public java.util.Date getDate_naissance() {
			return this.date_naissance;
		}

		public String niveau_socio_economique;

		public String getNiveau_socio_economique() {
			return this.niveau_socio_economique;
		}

		public Integer id_filiere;

		public Integer getId_filiere() {
			return this.id_filiere;
		}

		public Integer annee_entree;

		public Integer getAnnee_entree() {
			return this.annee_entree;
		}

		public String ville_origine;

		public String getVille_origine() {
			return this.ville_origine;
		}

		public String situation_matrimoniale;

		public String getSituation_matrimoniale() {
			return this.situation_matrimoniale;
		}

		public Integer nb_enfants_a_charge;

		public Integer getNb_enfants_a_charge() {
			return this.nb_enfants_a_charge;
		}

		public String estime_de_soi;

		public String getEstime_de_soi() {
			return this.estime_de_soi;
		}

		public String trait_personnalite;

		public String getTrait_personnalite() {
			return this.trait_personnalite;
		}

		public String mention_baccalaureat;

		public String getMention_baccalaureat() {
			return this.mention_baccalaureat;
		}

		public String serie_terminale;

		public String getSerie_terminale() {
			return this.serie_terminale;
		}

		public Integer decrochage_anterieur;

		public Integer getDecrochage_anterieur() {
			return this.decrochage_anterieur;
		}

		public String frequence_etude_lecons;

		public String getFrequence_etude_lecons() {
			return this.frequence_etude_lecons;
		}

		public String interet_lecture_ecriture;

		public String getInteret_lecture_ecriture() {
			return this.interet_lecture_ecriture;
		}

		public String adresse_email;

		public String getAdresse_email() {
			return this.adresse_email;
		}

		public String telephone;

		public String getTelephone() {
			return this.telephone;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.id_etudiant;

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final after_tFileInputDelimited_4Struct other = (after_tFileInputDelimited_4Struct) obj;

			if (this.id_etudiant != other.id_etudiant)
				return false;

			return true;
		}

		public void copyDataTo(after_tFileInputDelimited_4Struct other) {

			other.id_etudiant = this.id_etudiant;
			other.matricule = this.matricule;
			other.email_etudiant = this.email_etudiant;
			other.nom = this.nom;
			other.prenom = this.prenom;
			other.genre = this.genre;
			other.date_naissance = this.date_naissance;
			other.niveau_socio_economique = this.niveau_socio_economique;
			other.id_filiere = this.id_filiere;
			other.annee_entree = this.annee_entree;
			other.ville_origine = this.ville_origine;
			other.situation_matrimoniale = this.situation_matrimoniale;
			other.nb_enfants_a_charge = this.nb_enfants_a_charge;
			other.estime_de_soi = this.estime_de_soi;
			other.trait_personnalite = this.trait_personnalite;
			other.mention_baccalaureat = this.mention_baccalaureat;
			other.serie_terminale = this.serie_terminale;
			other.decrochage_anterieur = this.decrochage_anterieur;
			other.frequence_etude_lecons = this.frequence_etude_lecons;
			other.interet_lecture_ecriture = this.interet_lecture_ecriture;
			other.adresse_email = this.adresse_email;
			other.telephone = this.telephone;

		}

		public void copyKeysDataTo(after_tFileInputDelimited_4Struct other) {

			other.id_etudiant = this.id_etudiant;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_13_Score_Risque.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_13_Score_Risque.length == 0) {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_13_Score_Risque.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_13_Score_Risque.length == 0) {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_13_Score_Risque = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_13_Score_Risque, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_etudiant = dis.readInt();

					this.matricule = readString(dis);

					this.email_etudiant = readString(dis);

					this.nom = readString(dis);

					this.prenom = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.genre = null;
					} else {
						this.genre = dis.readChar();
					}

					this.date_naissance = readDate(dis);

					this.niveau_socio_economique = readString(dis);

					this.id_filiere = readInteger(dis);

					this.annee_entree = readInteger(dis);

					this.ville_origine = readString(dis);

					this.situation_matrimoniale = readString(dis);

					this.nb_enfants_a_charge = readInteger(dis);

					this.estime_de_soi = readString(dis);

					this.trait_personnalite = readString(dis);

					this.mention_baccalaureat = readString(dis);

					this.serie_terminale = readString(dis);

					this.decrochage_anterieur = readInteger(dis);

					this.frequence_etude_lecons = readString(dis);

					this.interet_lecture_ecriture = readString(dis);

					this.adresse_email = readString(dis);

					this.telephone = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_13_Score_Risque) {

				try {

					int length = 0;

					this.id_etudiant = dis.readInt();

					this.matricule = readString(dis);

					this.email_etudiant = readString(dis);

					this.nom = readString(dis);

					this.prenom = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.genre = null;
					} else {
						this.genre = dis.readChar();
					}

					this.date_naissance = readDate(dis);

					this.niveau_socio_economique = readString(dis);

					this.id_filiere = readInteger(dis);

					this.annee_entree = readInteger(dis);

					this.ville_origine = readString(dis);

					this.situation_matrimoniale = readString(dis);

					this.nb_enfants_a_charge = readInteger(dis);

					this.estime_de_soi = readString(dis);

					this.trait_personnalite = readString(dis);

					this.mention_baccalaureat = readString(dis);

					this.serie_terminale = readString(dis);

					this.decrochage_anterieur = readInteger(dis);

					this.frequence_etude_lecons = readString(dis);

					this.interet_lecture_ecriture = readString(dis);

					this.adresse_email = readString(dis);

					this.telephone = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.id_etudiant);

				// String

				writeString(this.matricule, dos);

				// String

				writeString(this.email_etudiant, dos);

				// String

				writeString(this.nom, dos);

				// String

				writeString(this.prenom, dos);

				// Character

				if (this.genre == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.genre);
				}

				// java.util.Date

				writeDate(this.date_naissance, dos);

				// String

				writeString(this.niveau_socio_economique, dos);

				// Integer

				writeInteger(this.id_filiere, dos);

				// Integer

				writeInteger(this.annee_entree, dos);

				// String

				writeString(this.ville_origine, dos);

				// String

				writeString(this.situation_matrimoniale, dos);

				// Integer

				writeInteger(this.nb_enfants_a_charge, dos);

				// String

				writeString(this.estime_de_soi, dos);

				// String

				writeString(this.trait_personnalite, dos);

				// String

				writeString(this.mention_baccalaureat, dos);

				// String

				writeString(this.serie_terminale, dos);

				// Integer

				writeInteger(this.decrochage_anterieur, dos);

				// String

				writeString(this.frequence_etude_lecons, dos);

				// String

				writeString(this.interet_lecture_ecriture, dos);

				// String

				writeString(this.adresse_email, dos);

				// String

				writeString(this.telephone, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.id_etudiant);

				// String

				writeString(this.matricule, dos);

				// String

				writeString(this.email_etudiant, dos);

				// String

				writeString(this.nom, dos);

				// String

				writeString(this.prenom, dos);

				// Character

				if (this.genre == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.genre);
				}

				// java.util.Date

				writeDate(this.date_naissance, dos);

				// String

				writeString(this.niveau_socio_economique, dos);

				// Integer

				writeInteger(this.id_filiere, dos);

				// Integer

				writeInteger(this.annee_entree, dos);

				// String

				writeString(this.ville_origine, dos);

				// String

				writeString(this.situation_matrimoniale, dos);

				// Integer

				writeInteger(this.nb_enfants_a_charge, dos);

				// String

				writeString(this.estime_de_soi, dos);

				// String

				writeString(this.trait_personnalite, dos);

				// String

				writeString(this.mention_baccalaureat, dos);

				// String

				writeString(this.serie_terminale, dos);

				// Integer

				writeInteger(this.decrochage_anterieur, dos);

				// String

				writeString(this.frequence_etude_lecons, dos);

				// String

				writeString(this.interet_lecture_ecriture, dos);

				// String

				writeString(this.adresse_email, dos);

				// String

				writeString(this.telephone, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_etudiant=" + String.valueOf(id_etudiant));
			sb.append(",matricule=" + matricule);
			sb.append(",email_etudiant=" + email_etudiant);
			sb.append(",nom=" + nom);
			sb.append(",prenom=" + prenom);
			sb.append(",genre=" + String.valueOf(genre));
			sb.append(",date_naissance=" + String.valueOf(date_naissance));
			sb.append(",niveau_socio_economique=" + niveau_socio_economique);
			sb.append(",id_filiere=" + String.valueOf(id_filiere));
			sb.append(",annee_entree=" + String.valueOf(annee_entree));
			sb.append(",ville_origine=" + ville_origine);
			sb.append(",situation_matrimoniale=" + situation_matrimoniale);
			sb.append(",nb_enfants_a_charge=" + String.valueOf(nb_enfants_a_charge));
			sb.append(",estime_de_soi=" + estime_de_soi);
			sb.append(",trait_personnalite=" + trait_personnalite);
			sb.append(",mention_baccalaureat=" + mention_baccalaureat);
			sb.append(",serie_terminale=" + serie_terminale);
			sb.append(",decrochage_anterieur=" + String.valueOf(decrochage_anterieur));
			sb.append(",frequence_etude_lecons=" + frequence_etude_lecons);
			sb.append(",interet_lecture_ecriture=" + interet_lecture_ecriture);
			sb.append(",adresse_email=" + adresse_email);
			sb.append(",telephone=" + telephone);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputDelimited_4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id_etudiant, other.id_etudiant);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tFileInputDelimited_1Process(globalMap);
				tFileInputDelimited_2Process(globalMap);
				tFileInputDelimited_3Process(globalMap);

				row4Struct row4 = new row4Struct();
				etudiant_OutStruct etudiant_Out = new etudiant_OutStruct();
				score_risque_OutputStruct score_risque_Output = new score_risque_OutputStruct();

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "score_risque_Output");
				}

				int tos_count_tDBOutput_1 = 0;

				int nb_line_tDBOutput_1 = 0;
				int nb_line_update_tDBOutput_1 = 0;
				int nb_line_inserted_tDBOutput_1 = 0;
				int nb_line_deleted_tDBOutput_1 = 0;
				int nb_line_rejected_tDBOutput_1 = 0;

				int deletedCount_tDBOutput_1 = 0;
				int updatedCount_tDBOutput_1 = 0;
				int insertedCount_tDBOutput_1 = 0;
				int rowsToCommitCount_tDBOutput_1 = 0;
				int rejectedCount_tDBOutput_1 = 0;

				String tableName_tDBOutput_1 = "fait_risque_etudiant";
				boolean whetherReject_tDBOutput_1 = false;

				java.util.Calendar calendar_tDBOutput_1 = java.util.Calendar.getInstance();
				calendar_tDBOutput_1.set(1, 0, 1, 0, 0, 0);
				long year1_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
				calendar_tDBOutput_1.set(10000, 0, 1, 0, 0, 0);
				long year10000_tDBOutput_1 = calendar_tDBOutput_1.getTime().getTime();
				long date_tDBOutput_1;

				java.sql.Connection conn_tDBOutput_1 = null;

				String properties_tDBOutput_1 = "noDatetimeStringSync=true&enabledTLSProtocols=TLSv1.2,TLSv1.1,TLSv1";
				if (properties_tDBOutput_1 == null || properties_tDBOutput_1.trim().length() == 0) {
					properties_tDBOutput_1 = "rewriteBatchedStatements=true&allowLoadLocalInfile=true";
				} else {
					if (!properties_tDBOutput_1.contains("rewriteBatchedStatements=")) {
						properties_tDBOutput_1 += "&rewriteBatchedStatements=true";
					}

					if (!properties_tDBOutput_1.contains("allowLoadLocalInfile=")) {
						properties_tDBOutput_1 += "&allowLoadLocalInfile=true";
					}
				}

				String url_tDBOutput_1 = "jdbc:mysql://" + "127.0.0.1" + ":" + "3306" + "/" + "dwh_education" + "?"
						+ properties_tDBOutput_1;

				String driverClass_tDBOutput_1 = "com.mysql.cj.jdbc.Driver";

				String dbUser_tDBOutput_1 = "root";

				final String decryptedPassword_tDBOutput_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:2Nx9BihZvJ4GA23Za9SdaTRDwj/fHK9iW7b9hw==");

				String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;
				java.lang.Class.forName(driverClass_tDBOutput_1);

				conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1, dbUser_tDBOutput_1,
						dbPwd_tDBOutput_1);

				resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);
				conn_tDBOutput_1.setAutoCommit(false);
				int commitEvery_tDBOutput_1 = 10000;
				int commitCounter_tDBOutput_1 = 0;

				int count_tDBOutput_1 = 0;

				java.sql.DatabaseMetaData dbMetaData_tDBOutput_1 = conn_tDBOutput_1.getMetaData();
				java.sql.ResultSet rsTable_tDBOutput_1 = dbMetaData_tDBOutput_1.getTables("dwh_education", null, null,
						new String[] { "TABLE" });
				boolean whetherExist_tDBOutput_1 = false;
				while (rsTable_tDBOutput_1.next()) {
					String table_tDBOutput_1 = rsTable_tDBOutput_1.getString("TABLE_NAME");
					if (table_tDBOutput_1.equalsIgnoreCase("fait_risque_etudiant")) {
						whetherExist_tDBOutput_1 = true;
						break;
					}
				}
				if (!whetherExist_tDBOutput_1) {
					try (java.sql.Statement stmtCreate_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
						stmtCreate_tDBOutput_1.execute("CREATE TABLE `" + tableName_tDBOutput_1
								+ "`(`id_etudiant` INT(2)   not null ,`isolement_social` INT(1)  ,`victime_harcelement` INT(1)  ,`difficultes_financieres_recurrentes` INT(1)  ,`estime_de_soi` VARCHAR(8)  ,`frequence_etude_lecons` VARCHAR(15)  ,`score_risque` INT(0)  ,`niveau_risque` VARCHAR(0)  ,primary key(`id_etudiant`))");
					}
				}
				String replace_tDBOutput_1 = "REPLACE INTO `" + "fait_risque_etudiant"
						+ "` (`id_etudiant`,`isolement_social`,`victime_harcelement`,`difficultes_financieres_recurrentes`,`estime_de_soi`,`frequence_etude_lecons`,`score_risque`,`niveau_risque`) VALUES (?,?,?,?,?,?,?,?)";
				java.sql.PreparedStatement pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(replace_tDBOutput_1);
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tMap_4 begin ] start
				 */

				ok_Hash.put("tMap_4", false);
				start_Hash.put("tMap_4", System.currentTimeMillis());

				currentComponent = "tMap_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "etudiant_Out");
				}

				int tos_count_tMap_4 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<finance_OutStruct> tHash_Lookup_finance_Out = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<finance_OutStruct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<finance_OutStruct>) globalMap
						.get("tHash_Lookup_finance_Out"));

				finance_OutStruct finance_OutHashKey = new finance_OutStruct();
				finance_OutStruct finance_OutDefault = new finance_OutStruct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<sociale_OutStruct> tHash_Lookup_sociale_Out = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<sociale_OutStruct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<sociale_OutStruct>) globalMap
						.get("tHash_Lookup_sociale_Out"));

				sociale_OutStruct sociale_OutHashKey = new sociale_OutStruct();
				sociale_OutStruct sociale_OutDefault = new sociale_OutStruct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<difficulte_OutStruct> tHash_Lookup_difficulte_Out = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<difficulte_OutStruct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<difficulte_OutStruct>) globalMap
						.get("tHash_Lookup_difficulte_Out"));

				difficulte_OutStruct difficulte_OutHashKey = new difficulte_OutStruct();
				difficulte_OutStruct difficulte_OutDefault = new difficulte_OutStruct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_4__Struct {
					Integer score;
				}
				Var__tMap_4__Struct Var__tMap_4 = new Var__tMap_4__Struct();
// ###############################

// ###############################
// # Outputs initialization
				score_risque_OutputStruct score_risque_Output_tmp = new score_risque_OutputStruct();
// ###############################

				/**
				 * [tMap_4 begin ] stop
				 */

				/**
				 * [tMap_5 begin ] start
				 */

				ok_Hash.put("tMap_5", false);
				start_Hash.put("tMap_5", System.currentTimeMillis());

				currentComponent = "tMap_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tMap_5 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_5__Struct {
				}
				Var__tMap_5__Struct Var__tMap_5 = new Var__tMap_5__Struct();
// ###############################

// ###############################
// # Outputs initialization
				etudiant_OutStruct etudiant_Out_tmp = new etudiant_OutStruct();
// ###############################

				/**
				 * [tMap_5 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_4 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_4", false);
				start_Hash.put("tFileInputDelimited_4", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_4";

				int tos_count_tFileInputDelimited_4 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_4 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_4 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_4 = null;
				int limit_tFileInputDelimited_4 = -1;
				try {

					Object filename_tFileInputDelimited_4 = "C:/home/claude/education_bi_v2/dim_etudiant.csv";
					if (filename_tFileInputDelimited_4 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_4 = 0, random_value_tFileInputDelimited_4 = -1;
						if (footer_value_tFileInputDelimited_4 > 0 || random_value_tFileInputDelimited_4 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_4 = new org.talend.fileprocess.FileInputDelimited(
								"C:/home/claude/education_bi_v2/dim_etudiant.csv", "UTF-8", ",", "\n", true, 1, 0,
								limit_tFileInputDelimited_4, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_4 != null && fid_tFileInputDelimited_4.nextRecord()) {
						rowstate_tFileInputDelimited_4.reset();

						row4 = null;

						boolean whetherReject_tFileInputDelimited_4 = false;
						row4 = new row4Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_4 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_4 = 0;

							temp = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);
							if (temp.length() > 0) {

								try {

									row4.id_etudiant = ParserUtils.parseTo_int(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_4) {
									globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE",
											ex_tFileInputDelimited_4.getMessage());
									rowstate_tFileInputDelimited_4.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"id_etudiant", "row4", temp, ex_tFileInputDelimited_4),
											ex_tFileInputDelimited_4));
								}

							} else {

								rowstate_tFileInputDelimited_4.setException(new RuntimeException(
										"Value is empty for column : 'id_etudiant' in 'row4' connection, value is invalid or this column should be nullable or have a default value."));

							}

							columnIndexWithD_tFileInputDelimited_4 = 1;

							row4.matricule = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 2;

							row4.email_etudiant = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 3;

							row4.nom = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 4;

							row4.prenom = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 5;

							temp = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);
							if (temp.length() > 0) {

								try {

									row4.genre = ParserUtils.parseTo_Character(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_4) {
									globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE",
											ex_tFileInputDelimited_4.getMessage());
									rowstate_tFileInputDelimited_4.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"genre", "row4", temp, ex_tFileInputDelimited_4),
											ex_tFileInputDelimited_4));
								}

							} else {

								row4.genre = null;

							}

							columnIndexWithD_tFileInputDelimited_4 = 6;

							temp = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);
							if (temp.length() > 0) {

								try {

									row4.date_naissance = ParserUtils.parseTo_Date(temp, "dd-MM-yyyy");

								} catch (java.lang.Exception ex_tFileInputDelimited_4) {
									globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE",
											ex_tFileInputDelimited_4.getMessage());
									rowstate_tFileInputDelimited_4.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"date_naissance", "row4", temp, ex_tFileInputDelimited_4),
											ex_tFileInputDelimited_4));
								}

							} else {

								row4.date_naissance = null;

							}

							columnIndexWithD_tFileInputDelimited_4 = 7;

							row4.niveau_socio_economique = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 8;

							temp = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);
							if (temp.length() > 0) {

								try {

									row4.id_filiere = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_4) {
									globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE",
											ex_tFileInputDelimited_4.getMessage());
									rowstate_tFileInputDelimited_4.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"id_filiere", "row4", temp, ex_tFileInputDelimited_4),
											ex_tFileInputDelimited_4));
								}

							} else {

								row4.id_filiere = null;

							}

							columnIndexWithD_tFileInputDelimited_4 = 9;

							temp = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);
							if (temp.length() > 0) {

								try {

									row4.annee_entree = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_4) {
									globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE",
											ex_tFileInputDelimited_4.getMessage());
									rowstate_tFileInputDelimited_4.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"annee_entree", "row4", temp, ex_tFileInputDelimited_4),
											ex_tFileInputDelimited_4));
								}

							} else {

								row4.annee_entree = null;

							}

							columnIndexWithD_tFileInputDelimited_4 = 10;

							row4.ville_origine = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 11;

							row4.situation_matrimoniale = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 12;

							temp = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);
							if (temp.length() > 0) {

								try {

									row4.nb_enfants_a_charge = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_4) {
									globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE",
											ex_tFileInputDelimited_4.getMessage());
									rowstate_tFileInputDelimited_4.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"nb_enfants_a_charge", "row4", temp, ex_tFileInputDelimited_4),
											ex_tFileInputDelimited_4));
								}

							} else {

								row4.nb_enfants_a_charge = null;

							}

							columnIndexWithD_tFileInputDelimited_4 = 13;

							row4.estime_de_soi = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 14;

							row4.trait_personnalite = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 15;

							row4.mention_baccalaureat = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 16;

							row4.serie_terminale = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 17;

							temp = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);
							if (temp.length() > 0) {

								try {

									row4.decrochage_anterieur = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_4) {
									globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE",
											ex_tFileInputDelimited_4.getMessage());
									rowstate_tFileInputDelimited_4.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"decrochage_anterieur", "row4", temp, ex_tFileInputDelimited_4),
											ex_tFileInputDelimited_4));
								}

							} else {

								row4.decrochage_anterieur = null;

							}

							columnIndexWithD_tFileInputDelimited_4 = 18;

							row4.frequence_etude_lecons = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 19;

							row4.interet_lecture_ecriture = fid_tFileInputDelimited_4
									.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 20;

							row4.adresse_email = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							columnIndexWithD_tFileInputDelimited_4 = 21;

							row4.telephone = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							if (rowstate_tFileInputDelimited_4.getException() != null) {
								throw rowstate_tFileInputDelimited_4.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_4 = true;

							System.err.println(e.getMessage());
							row4 = null;

						}

						/**
						 * [tFileInputDelimited_4 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_4 main ] start
						 */

						currentComponent = "tFileInputDelimited_4";

						tos_count_tFileInputDelimited_4++;

						/**
						 * [tFileInputDelimited_4 main ] stop
						 */

						/**
						 * [tFileInputDelimited_4 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_4";

						/**
						 * [tFileInputDelimited_4 process_data_begin ] stop
						 */
// Start of branch "row4"
						if (row4 != null) {

							/**
							 * [tMap_5 main ] start
							 */

							currentComponent = "tMap_5";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row4"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_5 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_5 = false;
							boolean mainRowRejected_tMap_5 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_5__Struct Var = Var__tMap_5;// ###############################
								// ###############################
								// # Output tables

								etudiant_Out = null;

// # Output table : 'etudiant_Out'
								etudiant_Out_tmp.id_etudiant = row4.id_etudiant;
								etudiant_Out_tmp.matricule = row4.matricule;
								etudiant_Out_tmp.email_etudiant = row4.email_etudiant;
								etudiant_Out_tmp.nom = row4.nom;
								etudiant_Out_tmp.prenom = row4.prenom;
								etudiant_Out_tmp.genre = row4.genre;
								etudiant_Out_tmp.date_naissance = row4.date_naissance;
								etudiant_Out_tmp.niveau_socio_economique = row4.niveau_socio_economique;
								etudiant_Out_tmp.id_filiere = row4.id_filiere;
								etudiant_Out_tmp.annee_entree = row4.annee_entree;
								etudiant_Out_tmp.ville_origine = row4.ville_origine;
								etudiant_Out_tmp.situation_matrimoniale = row4.situation_matrimoniale;
								etudiant_Out_tmp.nb_enfants_a_charge = row4.nb_enfants_a_charge;
								etudiant_Out_tmp.estime_de_soi = row4.estime_de_soi;
								etudiant_Out_tmp.trait_personnalite = row4.trait_personnalite;
								etudiant_Out_tmp.mention_baccalaureat = row4.mention_baccalaureat;
								etudiant_Out_tmp.serie_terminale = row4.serie_terminale;
								etudiant_Out_tmp.decrochage_anterieur = row4.decrochage_anterieur;
								etudiant_Out_tmp.frequence_etude_lecons = row4.frequence_etude_lecons;
								etudiant_Out_tmp.interet_lecture_ecriture = row4.interet_lecture_ecriture;
								etudiant_Out_tmp.adresse_email = row4.adresse_email;
								etudiant_Out_tmp.telephone = row4.telephone;
								etudiant_Out = etudiant_Out_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_5 = false;

							tos_count_tMap_5++;

							/**
							 * [tMap_5 main ] stop
							 */

							/**
							 * [tMap_5 process_data_begin ] start
							 */

							currentComponent = "tMap_5";

							/**
							 * [tMap_5 process_data_begin ] stop
							 */
// Start of branch "etudiant_Out"
							if (etudiant_Out != null) {

								/**
								 * [tMap_4 main ] start
								 */

								currentComponent = "tMap_4";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "etudiant_Out"

									);
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;

								// ###############################
								// # Input tables (lookups)
								boolean rejectedInnerJoin_tMap_4 = false;
								boolean mainRowRejected_tMap_4 = false;

								///////////////////////////////////////////////
								// Starting Lookup Table "finance_Out"
								///////////////////////////////////////////////

								boolean forceLoopfinance_Out = false;

								finance_OutStruct finance_OutObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_4) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_4 = false;

									finance_OutHashKey.id_etudiant = etudiant_Out.id_etudiant;

									finance_OutHashKey.hashCodeDirty = true;

									tHash_Lookup_finance_Out.lookup(finance_OutHashKey);

									if (!tHash_Lookup_finance_Out.hasNext()) { // G_TM_M_090

										rejectedInnerJoin_tMap_4 = true;

									} // G_TM_M_090

								} // G_TM_M_020

								if (tHash_Lookup_finance_Out != null
										&& tHash_Lookup_finance_Out.getCount(finance_OutHashKey) > 1) { // G 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
									// 'finance_Out' and it contains more one result from keys :
									// finance_Out.id_etudiant = '" + finance_OutHashKey.id_etudiant + "'");
								} // G 071

								finance_OutStruct finance_Out = null;

								finance_OutStruct fromLookup_finance_Out = null;
								finance_Out = finance_OutDefault;

								if (tHash_Lookup_finance_Out != null && tHash_Lookup_finance_Out.hasNext()) { // G 099

									fromLookup_finance_Out = tHash_Lookup_finance_Out.next();

								} // G 099

								if (fromLookup_finance_Out != null) {
									finance_Out = fromLookup_finance_Out;
								}

								///////////////////////////////////////////////
								// Starting Lookup Table "sociale_Out"
								///////////////////////////////////////////////

								boolean forceLoopsociale_Out = false;

								sociale_OutStruct sociale_OutObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_4) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_4 = false;

									sociale_OutHashKey.id_etudiant = etudiant_Out.id_etudiant;

									sociale_OutHashKey.hashCodeDirty = true;

									tHash_Lookup_sociale_Out.lookup(sociale_OutHashKey);

									if (!tHash_Lookup_sociale_Out.hasNext()) { // G_TM_M_090

										rejectedInnerJoin_tMap_4 = true;

									} // G_TM_M_090

								} // G_TM_M_020

								if (tHash_Lookup_sociale_Out != null
										&& tHash_Lookup_sociale_Out.getCount(sociale_OutHashKey) > 1) { // G 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
									// 'sociale_Out' and it contains more one result from keys :
									// sociale_Out.id_etudiant = '" + sociale_OutHashKey.id_etudiant + "'");
								} // G 071

								sociale_OutStruct sociale_Out = null;

								sociale_OutStruct fromLookup_sociale_Out = null;
								sociale_Out = sociale_OutDefault;

								if (tHash_Lookup_sociale_Out != null && tHash_Lookup_sociale_Out.hasNext()) { // G 099

									fromLookup_sociale_Out = tHash_Lookup_sociale_Out.next();

								} // G 099

								if (fromLookup_sociale_Out != null) {
									sociale_Out = fromLookup_sociale_Out;
								}

								///////////////////////////////////////////////
								// Starting Lookup Table "difficulte_Out"
								///////////////////////////////////////////////

								boolean forceLoopdifficulte_Out = false;

								difficulte_OutStruct difficulte_OutObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_4) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_4 = false;

									difficulte_OutHashKey.id_etudiant = etudiant_Out.id_etudiant;

									difficulte_OutHashKey.hashCodeDirty = true;

									tHash_Lookup_difficulte_Out.lookup(difficulte_OutHashKey);

									if (!tHash_Lookup_difficulte_Out.hasNext()) { // G_TM_M_090

										rejectedInnerJoin_tMap_4 = true;

									} // G_TM_M_090

								} // G_TM_M_020

								if (tHash_Lookup_difficulte_Out != null
										&& tHash_Lookup_difficulte_Out.getCount(difficulte_OutHashKey) > 1) { // G 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
									// 'difficulte_Out' and it contains more one result from keys :
									// difficulte_Out.id_etudiant = '" + difficulte_OutHashKey.id_etudiant + "'");
								} // G 071

								difficulte_OutStruct difficulte_Out = null;

								difficulte_OutStruct fromLookup_difficulte_Out = null;
								difficulte_Out = difficulte_OutDefault;

								if (tHash_Lookup_difficulte_Out != null && tHash_Lookup_difficulte_Out.hasNext()) { // G
																													// 099

									fromLookup_difficulte_Out = tHash_Lookup_difficulte_Out.next();

								} // G 099

								if (fromLookup_difficulte_Out != null) {
									difficulte_Out = fromLookup_difficulte_Out;
								}

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_4__Struct Var = Var__tMap_4;
									Var.score = Var.score = ((sociale_Out.isolement_social != null
											&& sociale_Out.isolement_social == 1 ? 2 : 0) +

											(sociale_Out.victime_harcelement != null
													&& sociale_Out.victime_harcelement == 1 ? 2 : 0)
											+

											(finance_Out.difficultes_financieres_recurrentes != null
													&& finance_Out.difficultes_financieres_recurrentes == 1 ? 2 : 0)
											+

											(etudiant_Out.estime_de_soi != null
													&& etudiant_Out.estime_de_soi.equals("Faible") ? 2 : 0)
											+

											(difficulte_Out.frequence_etude_lecons != null
													&& difficulte_Out.frequence_etude_lecons.equals("Jamais") ? 2 : 0));// ###############################
																														// ###############################
																														// #
																														// Output
																														// tables

									score_risque_Output = null;

									if (!rejectedInnerJoin_tMap_4) {

// # Output table : 'score_risque_Output'
										score_risque_Output_tmp.id_etudiant = etudiant_Out.id_etudiant;
										score_risque_Output_tmp.isolement_social = (sociale_Out.isolement_social == null)
												? 0
												: (sociale_Out.isolement_social > 0 ? 1 : 0);
										score_risque_Output_tmp.victime_harcelement = (sociale_Out.victime_harcelement == null)
												? 0
												: (sociale_Out.victime_harcelement > 0 ? 1 : 0);
										score_risque_Output_tmp.difficultes_financieres_recurrentes = (finance_Out.difficultes_financieres_recurrentes == null)
												? 0
												: (finance_Out.difficultes_financieres_recurrentes > 0 ? 1 : 0);
										score_risque_Output_tmp.estime_de_soi = (etudiant_Out.estime_de_soi == null)
												? "Moyenne"
												: etudiant_Out.estime_de_soi;
										score_risque_Output_tmp.frequence_etude_lecons = java.util.Arrays
												.asList("Jamais", "Rarement", "Parfois", "Régulièrement",
														"Intensivement")
												.contains(difficulte_Out.frequence_etude_lecons)
														? difficulte_Out.frequence_etude_lecons
														: "Parfois";
										score_risque_Output_tmp.score_risque = Var.score;
										score_risque_Output_tmp.niveau_risque = Var.score >= 8 ? "Risque élevé"
												: Var.score >= 4 ? "Risque moyen" : "Risque faible";
										score_risque_Output = score_risque_Output_tmp;
									} // closing inner join bracket (2)
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_4 = false;

								tos_count_tMap_4++;

								/**
								 * [tMap_4 main ] stop
								 */

								/**
								 * [tMap_4 process_data_begin ] start
								 */

								currentComponent = "tMap_4";

								/**
								 * [tMap_4 process_data_begin ] stop
								 */
// Start of branch "score_risque_Output"
								if (score_risque_Output != null) {

									/**
									 * [tDBOutput_1 main ] start
									 */

									currentComponent = "tDBOutput_1";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "score_risque_Output"

										);
									}

									whetherReject_tDBOutput_1 = false;
									pstmt_tDBOutput_1.setInt(1, score_risque_Output.id_etudiant);

									if (score_risque_Output.isolement_social == null) {
										pstmt_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_1.setInt(2, score_risque_Output.isolement_social);
									}

									if (score_risque_Output.victime_harcelement == null) {
										pstmt_tDBOutput_1.setNull(3, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_1.setInt(3, score_risque_Output.victime_harcelement);
									}

									if (score_risque_Output.difficultes_financieres_recurrentes == null) {
										pstmt_tDBOutput_1.setNull(4, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_1.setInt(4,
												score_risque_Output.difficultes_financieres_recurrentes);
									}

									if (score_risque_Output.estime_de_soi == null) {
										pstmt_tDBOutput_1.setNull(5, java.sql.Types.VARCHAR);
									} else {
										pstmt_tDBOutput_1.setString(5, score_risque_Output.estime_de_soi);
									}

									if (score_risque_Output.frequence_etude_lecons == null) {
										pstmt_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
									} else {
										pstmt_tDBOutput_1.setString(6, score_risque_Output.frequence_etude_lecons);
									}

									if (score_risque_Output.score_risque == null) {
										pstmt_tDBOutput_1.setNull(7, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_1.setInt(7, score_risque_Output.score_risque);
									}

									if (score_risque_Output.niveau_risque == null) {
										pstmt_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
									} else {
										pstmt_tDBOutput_1.setString(8, score_risque_Output.niveau_risque);
									}

									int replaceCount_tDBOutput_1 = 0;
									try {
										int processedCount_tDBOutput_1 = pstmt_tDBOutput_1.executeUpdate();
										replaceCount_tDBOutput_1 += processedCount_tDBOutput_1;
										rowsToCommitCount_tDBOutput_1 += processedCount_tDBOutput_1;
									} catch (java.lang.Exception e) {
										globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
										whetherReject_tDBOutput_1 = true;
										System.err.print(e.getMessage());
									}
									if (replaceCount_tDBOutput_1 == 1) {
										insertedCount_tDBOutput_1 += replaceCount_tDBOutput_1;
									} else {
										insertedCount_tDBOutput_1 += 1;
										deletedCount_tDBOutput_1 += replaceCount_tDBOutput_1 - 1;
									}
									commitCounter_tDBOutput_1++;

									if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {

										if (rowsToCommitCount_tDBOutput_1 != 0) {
										}
										conn_tDBOutput_1.commit();
										if (rowsToCommitCount_tDBOutput_1 != 0) {
											rowsToCommitCount_tDBOutput_1 = 0;
										}
										commitCounter_tDBOutput_1 = 0;

									}

									tos_count_tDBOutput_1++;

									/**
									 * [tDBOutput_1 main ] stop
									 */

									/**
									 * [tDBOutput_1 process_data_begin ] start
									 */

									currentComponent = "tDBOutput_1";

									/**
									 * [tDBOutput_1 process_data_begin ] stop
									 */

									/**
									 * [tDBOutput_1 process_data_end ] start
									 */

									currentComponent = "tDBOutput_1";

									/**
									 * [tDBOutput_1 process_data_end ] stop
									 */

								} // End of branch "score_risque_Output"

								/**
								 * [tMap_4 process_data_end ] start
								 */

								currentComponent = "tMap_4";

								/**
								 * [tMap_4 process_data_end ] stop
								 */

							} // End of branch "etudiant_Out"

							/**
							 * [tMap_5 process_data_end ] start
							 */

							currentComponent = "tMap_5";

							/**
							 * [tMap_5 process_data_end ] stop
							 */

						} // End of branch "row4"

						/**
						 * [tFileInputDelimited_4 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_4";

						/**
						 * [tFileInputDelimited_4 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_4 end ] start
						 */

						currentComponent = "tFileInputDelimited_4";

					}
				} finally {
					if (!((Object) ("C:/home/claude/education_bi_v2/dim_etudiant.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_4 != null) {
							fid_tFileInputDelimited_4.close();
						}
					}
					if (fid_tFileInputDelimited_4 != null) {
						globalMap.put("tFileInputDelimited_4_NB_LINE", fid_tFileInputDelimited_4.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_4", true);
				end_Hash.put("tFileInputDelimited_4", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_4 end ] stop
				 */

				/**
				 * [tMap_5 end ] start
				 */

				currentComponent = "tMap_5";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tMap_5", true);
				end_Hash.put("tMap_5", System.currentTimeMillis());

				/**
				 * [tMap_5 end ] stop
				 */

				/**
				 * [tMap_4 end ] start
				 */

				currentComponent = "tMap_4";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_finance_Out != null) {
					tHash_Lookup_finance_Out.endGet();
				}
				globalMap.remove("tHash_Lookup_finance_Out");

				if (tHash_Lookup_sociale_Out != null) {
					tHash_Lookup_sociale_Out.endGet();
				}
				globalMap.remove("tHash_Lookup_sociale_Out");

				if (tHash_Lookup_difficulte_Out != null) {
					tHash_Lookup_difficulte_Out.endGet();
				}
				globalMap.remove("tHash_Lookup_difficulte_Out");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "etudiant_Out");
				}

				ok_Hash.put("tMap_4", true);
				end_Hash.put("tMap_4", System.currentTimeMillis());

				/**
				 * [tMap_4 end ] stop
				 */

				/**
				 * [tDBOutput_1 end ] start
				 */

				currentComponent = "tDBOutput_1";

				if (pstmt_tDBOutput_1 != null) {

					pstmt_tDBOutput_1.close();
					resourceMap.remove("pstmt_tDBOutput_1");

				}
				resourceMap.put("statementClosed_tDBOutput_1", true);
				if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

				}
				conn_tDBOutput_1.commit();
				if (commitCounter_tDBOutput_1 > 0 && rowsToCommitCount_tDBOutput_1 != 0) {

					rowsToCommitCount_tDBOutput_1 = 0;
				}
				commitCounter_tDBOutput_1 = 0;

				conn_tDBOutput_1.close();

				resourceMap.put("finish_tDBOutput_1", true);

				nb_line_deleted_tDBOutput_1 = nb_line_deleted_tDBOutput_1 + deletedCount_tDBOutput_1;
				nb_line_update_tDBOutput_1 = nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
				nb_line_inserted_tDBOutput_1 = nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
				nb_line_rejected_tDBOutput_1 = nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;

				globalMap.put("tDBOutput_1_NB_LINE", nb_line_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_UPDATED", nb_line_update_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_DELETED", nb_line_deleted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "score_risque_Output");
				}

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_4"
			globalMap.remove("tHash_Lookup_sociale_Out");

			// free memory for "tMap_4"
			globalMap.remove("tHash_Lookup_difficulte_Out");

			// free memory for "tMap_4"
			globalMap.remove("tHash_Lookup_finance_Out");

			try {

				/**
				 * [tFileInputDelimited_4 finally ] start
				 */

				currentComponent = "tFileInputDelimited_4";

				/**
				 * [tFileInputDelimited_4 finally ] stop
				 */

				/**
				 * [tMap_5 finally ] start
				 */

				currentComponent = "tMap_5";

				/**
				 * [tMap_5 finally ] stop
				 */

				/**
				 * [tMap_4 finally ] start
				 */

				currentComponent = "tMap_4";

				/**
				 * [tMap_4 finally ] stop
				 */

				/**
				 * [tDBOutput_1 finally ] start
				 */

				currentComponent = "tDBOutput_1";

				try {
					if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
						if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_1")) != null) {
							pstmtToClose_tDBOutput_1.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_1") == null) {
						java.sql.Connection ctn_tDBOutput_1 = null;
						if ((ctn_tDBOutput_1 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_1")) != null) {
							try {
								ctn_tDBOutput_1.close();
							} catch (java.sql.SQLException sqlEx_tDBOutput_1) {
								String errorMessage_tDBOutput_1 = "failed to close the connection in tDBOutput_1 :"
										+ sqlEx_tDBOutput_1.getMessage();
								System.err.println(errorMessage_tDBOutput_1);
							}
						}
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final Job_13_Score_Risque Job_13_Score_RisqueClass = new Job_13_Score_Risque();

		int exitCode = Job_13_Score_RisqueClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = Job_13_Score_Risque.class.getClassLoader()
					.getResourceAsStream("projet_bi/job_13_score_risque_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Job_13_Score_Risque.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_4Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_4) {
			globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_4.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : Job_13_Score_Risque");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 284194 characters generated by Talend Open Studio for Data Integration on the
 * 10 mai 2026 à 23:20:56 WAT
 ************************************************************************************************/