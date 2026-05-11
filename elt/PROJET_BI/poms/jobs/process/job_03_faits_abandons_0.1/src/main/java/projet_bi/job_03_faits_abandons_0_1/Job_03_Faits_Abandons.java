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

package projet_bi.job_03_faits_abandons_0_1;

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
 * Job: Job_03_Faits_Abandons Purpose: Job_03_Faits_Abandons<br>
 * Description: Job_03_Faits_Abandons <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class Job_03_Faits_Abandons implements TalendJob {

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
	private final String jobName = "Job_03_Faits_Abandons";
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
					Job_03_Faits_Abandons.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Job_03_Faits_Abandons.this, new Object[] { e, currentComponent, globalMap });
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

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
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

	public void tAdvancedHash_etudiant_Out_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_filiere_Out_error(Exception exception, String errorComponent,
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

	public static class abandon_OutputStruct implements routines.system.IPersistableRow<abandon_OutputStruct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int id_abandon;

		public int getId_abandon() {
			return this.id_abandon;
		}

		public Integer id_etudiant;

		public Integer getId_etudiant() {
			return this.id_etudiant;
		}

		public Integer id_filiere;

		public Integer getId_filiere() {
			return this.id_filiere;
		}

		public String annee_scolaire;

		public String getAnnee_scolaire() {
			return this.annee_scolaire;
		}

		public Integer annee_abandon;

		public Integer getAnnee_abandon() {
			return this.annee_abandon;
		}

		public String motif_abandon;

		public String getMotif_abandon() {
			return this.motif_abandon;
		}

		public Integer semestre_abandon;

		public Integer getSemestre_abandon() {
			return this.semestre_abandon;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.id_abandon;

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
			final abandon_OutputStruct other = (abandon_OutputStruct) obj;

			if (this.id_abandon != other.id_abandon)
				return false;

			return true;
		}

		public void copyDataTo(abandon_OutputStruct other) {

			other.id_abandon = this.id_abandon;
			other.id_etudiant = this.id_etudiant;
			other.id_filiere = this.id_filiere;
			other.annee_scolaire = this.annee_scolaire;
			other.annee_abandon = this.annee_abandon;
			other.motif_abandon = this.motif_abandon;
			other.semestre_abandon = this.semestre_abandon;

		}

		public void copyKeysDataTo(abandon_OutputStruct other) {

			other.id_abandon = this.id_abandon;

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
				if (length > commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length == 0) {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length == 0) {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

				try {

					int length = 0;

					this.id_abandon = dis.readInt();

					this.id_etudiant = readInteger(dis);

					this.id_filiere = readInteger(dis);

					this.annee_scolaire = readString(dis);

					this.annee_abandon = readInteger(dis);

					this.motif_abandon = readString(dis);

					this.semestre_abandon = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

				try {

					int length = 0;

					this.id_abandon = dis.readInt();

					this.id_etudiant = readInteger(dis);

					this.id_filiere = readInteger(dis);

					this.annee_scolaire = readString(dis);

					this.annee_abandon = readInteger(dis);

					this.motif_abandon = readString(dis);

					this.semestre_abandon = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.id_abandon);

				// Integer

				writeInteger(this.id_etudiant, dos);

				// Integer

				writeInteger(this.id_filiere, dos);

				// String

				writeString(this.annee_scolaire, dos);

				// Integer

				writeInteger(this.annee_abandon, dos);

				// String

				writeString(this.motif_abandon, dos);

				// Integer

				writeInteger(this.semestre_abandon, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.id_abandon);

				// Integer

				writeInteger(this.id_etudiant, dos);

				// Integer

				writeInteger(this.id_filiere, dos);

				// String

				writeString(this.annee_scolaire, dos);

				// Integer

				writeInteger(this.annee_abandon, dos);

				// String

				writeString(this.motif_abandon, dos);

				// Integer

				writeInteger(this.semestre_abandon, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_abandon=" + String.valueOf(id_abandon));
			sb.append(",id_etudiant=" + String.valueOf(id_etudiant));
			sb.append(",id_filiere=" + String.valueOf(id_filiere));
			sb.append(",annee_scolaire=" + annee_scolaire);
			sb.append(",annee_abandon=" + String.valueOf(annee_abandon));
			sb.append(",motif_abandon=" + motif_abandon);
			sb.append(",semestre_abandon=" + String.valueOf(semestre_abandon));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(abandon_OutputStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id_abandon, other.id_abandon);
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

	public static class abandon_OutStruct implements routines.system.IPersistableRow<abandon_OutStruct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[0];

		public int id_abandon;

		public int getId_abandon() {
			return this.id_abandon;
		}

		public Integer id_etudiant;

		public Integer getId_etudiant() {
			return this.id_etudiant;
		}

		public Integer id_filiere;

		public Integer getId_filiere() {
			return this.id_filiere;
		}

		public String annee_scolaire;

		public String getAnnee_scolaire() {
			return this.annee_scolaire;
		}

		public Integer annee_abandon;

		public Integer getAnnee_abandon() {
			return this.annee_abandon;
		}

		public String motif_abandon;

		public String getMotif_abandon() {
			return this.motif_abandon;
		}

		public Integer semestre_abandon;

		public Integer getSemestre_abandon() {
			return this.semestre_abandon;
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
				if (length > commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length == 0) {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length == 0) {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

				try {

					int length = 0;

					this.id_abandon = dis.readInt();

					this.id_etudiant = readInteger(dis);

					this.id_filiere = readInteger(dis);

					this.annee_scolaire = readString(dis);

					this.annee_abandon = readInteger(dis);

					this.motif_abandon = readString(dis);

					this.semestre_abandon = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

				try {

					int length = 0;

					this.id_abandon = dis.readInt();

					this.id_etudiant = readInteger(dis);

					this.id_filiere = readInteger(dis);

					this.annee_scolaire = readString(dis);

					this.annee_abandon = readInteger(dis);

					this.motif_abandon = readString(dis);

					this.semestre_abandon = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.id_abandon);

				// Integer

				writeInteger(this.id_etudiant, dos);

				// Integer

				writeInteger(this.id_filiere, dos);

				// String

				writeString(this.annee_scolaire, dos);

				// Integer

				writeInteger(this.annee_abandon, dos);

				// String

				writeString(this.motif_abandon, dos);

				// Integer

				writeInteger(this.semestre_abandon, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.id_abandon);

				// Integer

				writeInteger(this.id_etudiant, dos);

				// Integer

				writeInteger(this.id_filiere, dos);

				// String

				writeString(this.annee_scolaire, dos);

				// Integer

				writeInteger(this.annee_abandon, dos);

				// String

				writeString(this.motif_abandon, dos);

				// Integer

				writeInteger(this.semestre_abandon, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_abandon=" + String.valueOf(id_abandon));
			sb.append(",id_etudiant=" + String.valueOf(id_etudiant));
			sb.append(",id_filiere=" + String.valueOf(id_filiere));
			sb.append(",annee_scolaire=" + annee_scolaire);
			sb.append(",annee_abandon=" + String.valueOf(annee_abandon));
			sb.append(",motif_abandon=" + motif_abandon);
			sb.append(",semestre_abandon=" + String.valueOf(semestre_abandon));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(abandon_OutStruct other) {

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

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[0];

		public int id_abandon;

		public int getId_abandon() {
			return this.id_abandon;
		}

		public Integer id_etudiant;

		public Integer getId_etudiant() {
			return this.id_etudiant;
		}

		public Integer id_filiere;

		public Integer getId_filiere() {
			return this.id_filiere;
		}

		public String annee_scolaire;

		public String getAnnee_scolaire() {
			return this.annee_scolaire;
		}

		public Integer annee_abandon;

		public Integer getAnnee_abandon() {
			return this.annee_abandon;
		}

		public String motif_abandon;

		public String getMotif_abandon() {
			return this.motif_abandon;
		}

		public Integer semestre_abandon;

		public Integer getSemestre_abandon() {
			return this.semestre_abandon;
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
				if (length > commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length == 0) {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length == 0) {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

				try {

					int length = 0;

					this.id_abandon = dis.readInt();

					this.id_etudiant = readInteger(dis);

					this.id_filiere = readInteger(dis);

					this.annee_scolaire = readString(dis);

					this.annee_abandon = readInteger(dis);

					this.motif_abandon = readString(dis);

					this.semestre_abandon = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

				try {

					int length = 0;

					this.id_abandon = dis.readInt();

					this.id_etudiant = readInteger(dis);

					this.id_filiere = readInteger(dis);

					this.annee_scolaire = readString(dis);

					this.annee_abandon = readInteger(dis);

					this.motif_abandon = readString(dis);

					this.semestre_abandon = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.id_abandon);

				// Integer

				writeInteger(this.id_etudiant, dos);

				// Integer

				writeInteger(this.id_filiere, dos);

				// String

				writeString(this.annee_scolaire, dos);

				// Integer

				writeInteger(this.annee_abandon, dos);

				// String

				writeString(this.motif_abandon, dos);

				// Integer

				writeInteger(this.semestre_abandon, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.id_abandon);

				// Integer

				writeInteger(this.id_etudiant, dos);

				// Integer

				writeInteger(this.id_filiere, dos);

				// String

				writeString(this.annee_scolaire, dos);

				// Integer

				writeInteger(this.annee_abandon, dos);

				// String

				writeString(this.motif_abandon, dos);

				// Integer

				writeInteger(this.semestre_abandon, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_abandon=" + String.valueOf(id_abandon));
			sb.append(",id_etudiant=" + String.valueOf(id_etudiant));
			sb.append(",id_filiere=" + String.valueOf(id_filiere));
			sb.append(",annee_scolaire=" + annee_scolaire);
			sb.append(",annee_abandon=" + String.valueOf(annee_abandon));
			sb.append(",motif_abandon=" + motif_abandon);
			sb.append(",semestre_abandon=" + String.valueOf(semestre_abandon));
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

	public static class after_tFileInputDelimited_1Struct
			implements routines.system.IPersistableRow<after_tFileInputDelimited_1Struct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int id_abandon;

		public int getId_abandon() {
			return this.id_abandon;
		}

		public Integer id_etudiant;

		public Integer getId_etudiant() {
			return this.id_etudiant;
		}

		public Integer id_filiere;

		public Integer getId_filiere() {
			return this.id_filiere;
		}

		public String annee_scolaire;

		public String getAnnee_scolaire() {
			return this.annee_scolaire;
		}

		public Integer annee_abandon;

		public Integer getAnnee_abandon() {
			return this.annee_abandon;
		}

		public String motif_abandon;

		public String getMotif_abandon() {
			return this.motif_abandon;
		}

		public Integer semestre_abandon;

		public Integer getSemestre_abandon() {
			return this.semestre_abandon;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.id_abandon;

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
			final after_tFileInputDelimited_1Struct other = (after_tFileInputDelimited_1Struct) obj;

			if (this.id_abandon != other.id_abandon)
				return false;

			return true;
		}

		public void copyDataTo(after_tFileInputDelimited_1Struct other) {

			other.id_abandon = this.id_abandon;
			other.id_etudiant = this.id_etudiant;
			other.id_filiere = this.id_filiere;
			other.annee_scolaire = this.annee_scolaire;
			other.annee_abandon = this.annee_abandon;
			other.motif_abandon = this.motif_abandon;
			other.semestre_abandon = this.semestre_abandon;

		}

		public void copyKeysDataTo(after_tFileInputDelimited_1Struct other) {

			other.id_abandon = this.id_abandon;

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
				if (length > commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length == 0) {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length == 0) {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

				try {

					int length = 0;

					this.id_abandon = dis.readInt();

					this.id_etudiant = readInteger(dis);

					this.id_filiere = readInteger(dis);

					this.annee_scolaire = readString(dis);

					this.annee_abandon = readInteger(dis);

					this.motif_abandon = readString(dis);

					this.semestre_abandon = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

				try {

					int length = 0;

					this.id_abandon = dis.readInt();

					this.id_etudiant = readInteger(dis);

					this.id_filiere = readInteger(dis);

					this.annee_scolaire = readString(dis);

					this.annee_abandon = readInteger(dis);

					this.motif_abandon = readString(dis);

					this.semestre_abandon = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.id_abandon);

				// Integer

				writeInteger(this.id_etudiant, dos);

				// Integer

				writeInteger(this.id_filiere, dos);

				// String

				writeString(this.annee_scolaire, dos);

				// Integer

				writeInteger(this.annee_abandon, dos);

				// String

				writeString(this.motif_abandon, dos);

				// Integer

				writeInteger(this.semestre_abandon, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.id_abandon);

				// Integer

				writeInteger(this.id_etudiant, dos);

				// Integer

				writeInteger(this.id_filiere, dos);

				// String

				writeString(this.annee_scolaire, dos);

				// Integer

				writeInteger(this.annee_abandon, dos);

				// String

				writeString(this.motif_abandon, dos);

				// Integer

				writeInteger(this.semestre_abandon, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_abandon=" + String.valueOf(id_abandon));
			sb.append(",id_etudiant=" + String.valueOf(id_etudiant));
			sb.append(",id_filiere=" + String.valueOf(id_filiere));
			sb.append(",annee_scolaire=" + annee_scolaire);
			sb.append(",annee_abandon=" + String.valueOf(annee_abandon));
			sb.append(",motif_abandon=" + motif_abandon);
			sb.append(",semestre_abandon=" + String.valueOf(semestre_abandon));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputDelimited_1Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id_abandon, other.id_abandon);
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

				tFileInputDelimited_2Process(globalMap);
				tFileInputDelimited_3Process(globalMap);

				row1Struct row1 = new row1Struct();
				abandon_OutStruct abandon_Out = new abandon_OutStruct();
				abandon_OutputStruct abandon_Output = new abandon_OutputStruct();

				/**
				 * [tDBOutput_1 begin ] start
				 */

				ok_Hash.put("tDBOutput_1", false);
				start_Hash.put("tDBOutput_1", System.currentTimeMillis());

				currentComponent = "tDBOutput_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "abandon_Output");
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

				String tableName_tDBOutput_1 = "fait_abandons";
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
						.decryptPassword("enc:routine.encryption.key.v1:VY3LzqrQKQgISORJAMjNGTAjgo8hjb85NP3Phw==");

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
					if (table_tDBOutput_1.equalsIgnoreCase("fait_abandons")) {
						whetherExist_tDBOutput_1 = true;
						break;
					}
				}
				if (!whetherExist_tDBOutput_1) {
					try (java.sql.Statement stmtCreate_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
						stmtCreate_tDBOutput_1.execute("CREATE TABLE `" + tableName_tDBOutput_1
								+ "`(`id_abandon` INT(2)   not null ,`id_etudiant` INT(3)  ,`id_filiere` INT(2)  ,`annee_scolaire` VARCHAR(7)  ,`annee_abandon` INT(4)  ,`motif_abandon` VARCHAR(25)  ,`semestre_abandon` INT(1)  ,primary key(`id_abandon`))");
					}
				}
				String replace_tDBOutput_1 = "REPLACE INTO `" + "fait_abandons"
						+ "` (`id_abandon`,`id_etudiant`,`id_filiere`,`annee_scolaire`,`annee_abandon`,`motif_abandon`,`semestre_abandon`) VALUES (?,?,?,?,?,?,?)";
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
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "abandon_Out");
				}

				int tos_count_tMap_4 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<etudiant_OutStruct> tHash_Lookup_etudiant_Out = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<etudiant_OutStruct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<etudiant_OutStruct>) globalMap
						.get("tHash_Lookup_etudiant_Out"));

				etudiant_OutStruct etudiant_OutHashKey = new etudiant_OutStruct();
				etudiant_OutStruct etudiant_OutDefault = new etudiant_OutStruct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<filiere_OutStruct> tHash_Lookup_filiere_Out = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<filiere_OutStruct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<filiere_OutStruct>) globalMap
						.get("tHash_Lookup_filiere_Out"));

				filiere_OutStruct filiere_OutHashKey = new filiere_OutStruct();
				filiere_OutStruct filiere_OutDefault = new filiere_OutStruct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_4__Struct {
				}
				Var__tMap_4__Struct Var__tMap_4 = new Var__tMap_4__Struct();
// ###############################

// ###############################
// # Outputs initialization
				abandon_OutputStruct abandon_Output_tmp = new abandon_OutputStruct();
// ###############################

				/**
				 * [tMap_4 begin ] stop
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
				abandon_OutStruct abandon_Out_tmp = new abandon_OutStruct();
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

					Object filename_tFileInputDelimited_1 = "C:/home/claude/education_bi_v2/fait_abandons.csv";
					if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_1 = 0, random_value_tFileInputDelimited_1 = -1;
						if (footer_value_tFileInputDelimited_1 > 0 || random_value_tFileInputDelimited_1 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_1 = new org.talend.fileprocess.FileInputDelimited(
								"C:/home/claude/education_bi_v2/fait_abandons.csv", "UTF-8", ",", "\n", true, 1, 0,
								limit_tFileInputDelimited_1, -1, false);
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

									row1.id_abandon = ParserUtils.parseTo_int(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"id_abandon", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								rowstate_tFileInputDelimited_1.setException(new RuntimeException(
										"Value is empty for column : 'id_abandon' in 'row1' connection, value is invalid or this column should be nullable or have a default value."));

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

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.id_filiere = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"id_filiere", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.id_filiere = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 3;

							row1.annee_scolaire = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 4;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.annee_abandon = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"annee_abandon", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.annee_abandon = null;

							}

							columnIndexWithD_tFileInputDelimited_1 = 5;

							row1.motif_abandon = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);

							columnIndexWithD_tFileInputDelimited_1 = 6;

							temp = fid_tFileInputDelimited_1.get(columnIndexWithD_tFileInputDelimited_1);
							if (temp.length() > 0) {

								try {

									row1.semestre_abandon = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_1) {
									globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
											ex_tFileInputDelimited_1.getMessage());
									rowstate_tFileInputDelimited_1.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"semestre_abandon", "row1", temp, ex_tFileInputDelimited_1),
											ex_tFileInputDelimited_1));
								}

							} else {

								row1.semestre_abandon = null;

							}

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

								abandon_Out = null;

// # Output table : 'abandon_Out'
								abandon_Out_tmp.id_abandon = row1.id_abandon;
								abandon_Out_tmp.id_etudiant = row1.id_etudiant;
								abandon_Out_tmp.id_filiere = row1.id_filiere;
								abandon_Out_tmp.annee_scolaire = row1.annee_scolaire;
								abandon_Out_tmp.annee_abandon = row1.annee_abandon;
								abandon_Out_tmp.motif_abandon = row1.motif_abandon;
								abandon_Out_tmp.semestre_abandon = row1.semestre_abandon;
								abandon_Out = abandon_Out_tmp;
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
// Start of branch "abandon_Out"
							if (abandon_Out != null) {

								/**
								 * [tMap_4 main ] start
								 */

								currentComponent = "tMap_4";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "abandon_Out"

									);
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;

								// ###############################
								// # Input tables (lookups)
								boolean rejectedInnerJoin_tMap_4 = false;
								boolean mainRowRejected_tMap_4 = false;

								///////////////////////////////////////////////
								// Starting Lookup Table "etudiant_Out"
								///////////////////////////////////////////////

								boolean forceLoopetudiant_Out = false;

								etudiant_OutStruct etudiant_OutObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_4) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_4 = false;

									Object exprKeyValue_etudiant_Out__id_etudiant = abandon_Out.id_etudiant;
									if (exprKeyValue_etudiant_Out__id_etudiant == null) {
										hasCasePrimitiveKeyWithNull_tMap_4 = true;
									} else {
										etudiant_OutHashKey.id_etudiant = (int) (Integer) exprKeyValue_etudiant_Out__id_etudiant;
									}

									etudiant_OutHashKey.hashCodeDirty = true;

									if (!hasCasePrimitiveKeyWithNull_tMap_4) { // G_TM_M_091

										tHash_Lookup_etudiant_Out.lookup(etudiant_OutHashKey);

									} // G_TM_M_091

									if (hasCasePrimitiveKeyWithNull_tMap_4 || !tHash_Lookup_etudiant_Out.hasNext()) { // G_TM_M_090

										rejectedInnerJoin_tMap_4 = true;

									} // G_TM_M_090

								} // G_TM_M_020

								if (tHash_Lookup_etudiant_Out != null
										&& tHash_Lookup_etudiant_Out.getCount(etudiant_OutHashKey) > 1) { // G 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
									// 'etudiant_Out' and it contains more one result from keys :
									// etudiant_Out.id_etudiant = '" + etudiant_OutHashKey.id_etudiant + "'");
								} // G 071

								etudiant_OutStruct etudiant_Out = null;

								etudiant_OutStruct fromLookup_etudiant_Out = null;
								etudiant_Out = etudiant_OutDefault;

								if (tHash_Lookup_etudiant_Out != null && tHash_Lookup_etudiant_Out.hasNext()) { // G 099

									fromLookup_etudiant_Out = tHash_Lookup_etudiant_Out.next();

								} // G 099

								if (fromLookup_etudiant_Out != null) {
									etudiant_Out = fromLookup_etudiant_Out;
								}

								///////////////////////////////////////////////
								// Starting Lookup Table "filiere_Out"
								///////////////////////////////////////////////

								boolean forceLoopfiliere_Out = false;

								filiere_OutStruct filiere_OutObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_4) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_4 = false;

									Object exprKeyValue_filiere_Out__id_filiere = abandon_Out.id_filiere;
									if (exprKeyValue_filiere_Out__id_filiere == null) {
										hasCasePrimitiveKeyWithNull_tMap_4 = true;
									} else {
										filiere_OutHashKey.id_filiere = (int) (Integer) exprKeyValue_filiere_Out__id_filiere;
									}

									filiere_OutHashKey.hashCodeDirty = true;

									if (!hasCasePrimitiveKeyWithNull_tMap_4) { // G_TM_M_091

										tHash_Lookup_filiere_Out.lookup(filiere_OutHashKey);

									} // G_TM_M_091

									if (hasCasePrimitiveKeyWithNull_tMap_4 || !tHash_Lookup_filiere_Out.hasNext()) { // G_TM_M_090

										rejectedInnerJoin_tMap_4 = true;

									} // G_TM_M_090

								} // G_TM_M_020

								if (tHash_Lookup_filiere_Out != null
										&& tHash_Lookup_filiere_Out.getCount(filiere_OutHashKey) > 1) { // G 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
									// 'filiere_Out' and it contains more one result from keys :
									// filiere_Out.id_filiere = '" + filiere_OutHashKey.id_filiere + "'");
								} // G 071

								filiere_OutStruct filiere_Out = null;

								filiere_OutStruct fromLookup_filiere_Out = null;
								filiere_Out = filiere_OutDefault;

								if (tHash_Lookup_filiere_Out != null && tHash_Lookup_filiere_Out.hasNext()) { // G 099

									fromLookup_filiere_Out = tHash_Lookup_filiere_Out.next();

								} // G 099

								if (fromLookup_filiere_Out != null) {
									filiere_Out = fromLookup_filiere_Out;
								}

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_4__Struct Var = Var__tMap_4;// ###############################
									// ###############################
									// # Output tables

									abandon_Output = null;

									if (!rejectedInnerJoin_tMap_4) {

// # Output table : 'abandon_Output'
										abandon_Output_tmp.id_abandon = abandon_Out.id_abandon;
										abandon_Output_tmp.id_etudiant = abandon_Out.id_etudiant;
										abandon_Output_tmp.id_filiere = abandon_Out.id_filiere;
										abandon_Output_tmp.annee_scolaire = (abandon_Out.annee_scolaire == null)
												? "2020-21"
												: abandon_Out.annee_scolaire.trim();
										abandon_Output_tmp.annee_abandon = (abandon_Out.annee_abandon == null
												|| abandon_Out.annee_abandon < 2000) ? 2020 : abandon_Out.annee_abandon;
										abandon_Output_tmp.motif_abandon = (abandon_Out.motif_abandon == null
												|| abandon_Out.motif_abandon.isEmpty()) ? "Non renseigné"
														: abandon_Out.motif_abandon.trim();
										abandon_Output_tmp.semestre_abandon = (abandon_Out.semestre_abandon == null
												|| abandon_Out.semestre_abandon < 1 || abandon_Out.semestre_abandon > 2)
														? 1
														: abandon_Out.semestre_abandon;
										abandon_Output = abandon_Output_tmp;
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
// Start of branch "abandon_Output"
								if (abandon_Output != null) {

									/**
									 * [tDBOutput_1 main ] start
									 */

									currentComponent = "tDBOutput_1";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "abandon_Output"

										);
									}

									whetherReject_tDBOutput_1 = false;
									pstmt_tDBOutput_1.setInt(1, abandon_Output.id_abandon);

									if (abandon_Output.id_etudiant == null) {
										pstmt_tDBOutput_1.setNull(2, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_1.setInt(2, abandon_Output.id_etudiant);
									}

									if (abandon_Output.id_filiere == null) {
										pstmt_tDBOutput_1.setNull(3, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_1.setInt(3, abandon_Output.id_filiere);
									}

									if (abandon_Output.annee_scolaire == null) {
										pstmt_tDBOutput_1.setNull(4, java.sql.Types.VARCHAR);
									} else {
										pstmt_tDBOutput_1.setString(4, abandon_Output.annee_scolaire);
									}

									if (abandon_Output.annee_abandon == null) {
										pstmt_tDBOutput_1.setNull(5, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_1.setInt(5, abandon_Output.annee_abandon);
									}

									if (abandon_Output.motif_abandon == null) {
										pstmt_tDBOutput_1.setNull(6, java.sql.Types.VARCHAR);
									} else {
										pstmt_tDBOutput_1.setString(6, abandon_Output.motif_abandon);
									}

									if (abandon_Output.semestre_abandon == null) {
										pstmt_tDBOutput_1.setNull(7, java.sql.Types.INTEGER);
									} else {
										pstmt_tDBOutput_1.setInt(7, abandon_Output.semestre_abandon);
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

								} // End of branch "abandon_Output"

								/**
								 * [tMap_4 process_data_end ] start
								 */

								currentComponent = "tMap_4";

								/**
								 * [tMap_4 process_data_end ] stop
								 */

							} // End of branch "abandon_Out"

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
					if (!((Object) ("C:/home/claude/education_bi_v2/fait_abandons.csv") instanceof java.io.InputStream)) {
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
				 * [tMap_4 end ] start
				 */

				currentComponent = "tMap_4";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_etudiant_Out != null) {
					tHash_Lookup_etudiant_Out.endGet();
				}
				globalMap.remove("tHash_Lookup_etudiant_Out");

				if (tHash_Lookup_filiere_Out != null) {
					tHash_Lookup_filiere_Out.endGet();
				}
				globalMap.remove("tHash_Lookup_filiere_Out");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "abandon_Out");
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
					runStat.updateStat(resourceMap, iterateId, 2, 0, "abandon_Output");
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
			globalMap.remove("tHash_Lookup_etudiant_Out");

			// free memory for "tMap_4"
			globalMap.remove("tHash_Lookup_filiere_Out");

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

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public static class etudiant_OutStruct
			implements routines.system.IPersistableComparableLookupRow<etudiant_OutStruct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[0];
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
			final etudiant_OutStruct other = (etudiant_OutStruct) obj;

			if (this.id_etudiant != other.id_etudiant)
				return false;

			return true;
		}

		public void copyDataTo(etudiant_OutStruct other) {

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

		public void copyKeysDataTo(etudiant_OutStruct other) {

			other.id_etudiant = this.id_etudiant;

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

		private java.util.Date readDate(DataInputStream dis, ObjectInputStream ois) throws IOException {
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

		private java.util.Date readDate(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
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

		private void writeDate(java.util.Date date1, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
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

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

				try {

					int length = 0;

					this.id_etudiant = dis.readInt();

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

				try {

					int length = 0;

					this.id_etudiant = dis.readInt();

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.id_etudiant);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.id_etudiant);

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

				this.matricule = readString(dis, ois);

				this.email_etudiant = readString(dis, ois);

				this.nom = readString(dis, ois);

				this.prenom = readString(dis, ois);

				length = dis.readByte();
				if (length == -1) {
					this.genre = null;
				} else {
					this.genre = dis.readChar();
				}

				this.date_naissance = readDate(dis, ois);

				this.niveau_socio_economique = readString(dis, ois);

				this.id_filiere = readInteger(dis, ois);

				this.annee_entree = readInteger(dis, ois);

				this.ville_origine = readString(dis, ois);

				this.situation_matrimoniale = readString(dis, ois);

				this.nb_enfants_a_charge = readInteger(dis, ois);

				this.estime_de_soi = readString(dis, ois);

				this.trait_personnalite = readString(dis, ois);

				this.mention_baccalaureat = readString(dis, ois);

				this.serie_terminale = readString(dis, ois);

				this.decrochage_anterieur = readInteger(dis, ois);

				this.frequence_etude_lecons = readString(dis, ois);

				this.interet_lecture_ecriture = readString(dis, ois);

				this.adresse_email = readString(dis, ois);

				this.telephone = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.matricule = readString(dis, objectIn);

				this.email_etudiant = readString(dis, objectIn);

				this.nom = readString(dis, objectIn);

				this.prenom = readString(dis, objectIn);

				length = objectIn.readByte();
				if (length == -1) {
					this.genre = null;
				} else {
					this.genre = objectIn.readChar();
				}

				this.date_naissance = readDate(dis, objectIn);

				this.niveau_socio_economique = readString(dis, objectIn);

				this.id_filiere = readInteger(dis, objectIn);

				this.annee_entree = readInteger(dis, objectIn);

				this.ville_origine = readString(dis, objectIn);

				this.situation_matrimoniale = readString(dis, objectIn);

				this.nb_enfants_a_charge = readInteger(dis, objectIn);

				this.estime_de_soi = readString(dis, objectIn);

				this.trait_personnalite = readString(dis, objectIn);

				this.mention_baccalaureat = readString(dis, objectIn);

				this.serie_terminale = readString(dis, objectIn);

				this.decrochage_anterieur = readInteger(dis, objectIn);

				this.frequence_etude_lecons = readString(dis, objectIn);

				this.interet_lecture_ecriture = readString(dis, objectIn);

				this.adresse_email = readString(dis, objectIn);

				this.telephone = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.matricule, dos, oos);

				writeString(this.email_etudiant, dos, oos);

				writeString(this.nom, dos, oos);

				writeString(this.prenom, dos, oos);

				if (this.genre == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeChar(this.genre);
				}

				writeDate(this.date_naissance, dos, oos);

				writeString(this.niveau_socio_economique, dos, oos);

				writeInteger(this.id_filiere, dos, oos);

				writeInteger(this.annee_entree, dos, oos);

				writeString(this.ville_origine, dos, oos);

				writeString(this.situation_matrimoniale, dos, oos);

				writeInteger(this.nb_enfants_a_charge, dos, oos);

				writeString(this.estime_de_soi, dos, oos);

				writeString(this.trait_personnalite, dos, oos);

				writeString(this.mention_baccalaureat, dos, oos);

				writeString(this.serie_terminale, dos, oos);

				writeInteger(this.decrochage_anterieur, dos, oos);

				writeString(this.frequence_etude_lecons, dos, oos);

				writeString(this.interet_lecture_ecriture, dos, oos);

				writeString(this.adresse_email, dos, oos);

				writeString(this.telephone, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.matricule, dos, objectOut);

				writeString(this.email_etudiant, dos, objectOut);

				writeString(this.nom, dos, objectOut);

				writeString(this.prenom, dos, objectOut);

				if (this.genre == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeChar(this.genre);
				}

				writeDate(this.date_naissance, dos, objectOut);

				writeString(this.niveau_socio_economique, dos, objectOut);

				writeInteger(this.id_filiere, dos, objectOut);

				writeInteger(this.annee_entree, dos, objectOut);

				writeString(this.ville_origine, dos, objectOut);

				writeString(this.situation_matrimoniale, dos, objectOut);

				writeInteger(this.nb_enfants_a_charge, dos, objectOut);

				writeString(this.estime_de_soi, dos, objectOut);

				writeString(this.trait_personnalite, dos, objectOut);

				writeString(this.mention_baccalaureat, dos, objectOut);

				writeString(this.serie_terminale, dos, objectOut);

				writeInteger(this.decrochage_anterieur, dos, objectOut);

				writeString(this.frequence_etude_lecons, dos, objectOut);

				writeString(this.interet_lecture_ecriture, dos, objectOut);

				writeString(this.adresse_email, dos, objectOut);

				writeString(this.telephone, dos, objectOut);

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
		final static byte[] commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[0];

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
				if (length > commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length == 0) {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length == 0) {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

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

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

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
				etudiant_OutStruct etudiant_Out = new etudiant_OutStruct();

				/**
				 * [tAdvancedHash_etudiant_Out begin ] start
				 */

				ok_Hash.put("tAdvancedHash_etudiant_Out", false);
				start_Hash.put("tAdvancedHash_etudiant_Out", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_etudiant_Out";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "etudiant_Out");
				}

				int tos_count_tAdvancedHash_etudiant_Out = 0;

				// connection name:etudiant_Out
				// source node:tMap_2 - inputs:(row2) outputs:(etudiant_Out,etudiant_Out) |
				// target node:tAdvancedHash_etudiant_Out - inputs:(etudiant_Out) outputs:()
				// linked node: tMap_4 - inputs:(abandon_Out,etudiant_Out,filiere_Out)
				// outputs:(abandon_Output)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_etudiant_Out = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<etudiant_OutStruct> tHash_Lookup_etudiant_Out = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<etudiant_OutStruct>getLookup(matchingModeEnum_etudiant_Out);

				globalMap.put("tHash_Lookup_etudiant_Out", tHash_Lookup_etudiant_Out);

				/**
				 * [tAdvancedHash_etudiant_Out begin ] stop
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
				etudiant_OutStruct etudiant_Out_tmp = new etudiant_OutStruct();
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

					Object filename_tFileInputDelimited_2 = "C:/home/claude/education_bi_v2/dim_etudiant.csv";
					if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_2 = 0, random_value_tFileInputDelimited_2 = -1;
						if (footer_value_tFileInputDelimited_2 > 0 || random_value_tFileInputDelimited_2 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_2 = new org.talend.fileprocess.FileInputDelimited(
								"C:/home/claude/education_bi_v2/dim_etudiant.csv", "UTF-8", ",", "\n", true, 1, 0,
								limit_tFileInputDelimited_2, -1, false);
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

									row2.id_etudiant = ParserUtils.parseTo_int(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"id_etudiant", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								rowstate_tFileInputDelimited_2.setException(new RuntimeException(
										"Value is empty for column : 'id_etudiant' in 'row2' connection, value is invalid or this column should be nullable or have a default value."));

							}

							columnIndexWithD_tFileInputDelimited_2 = 1;

							row2.matricule = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 2;

							row2.email_etudiant = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 3;

							row2.nom = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 4;

							row2.prenom = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 5;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.genre = ParserUtils.parseTo_Character(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"genre", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.genre = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 6;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.date_naissance = ParserUtils.parseTo_Date(temp, "dd-MM-yyyy");

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"date_naissance", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.date_naissance = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 7;

							row2.niveau_socio_economique = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 8;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.id_filiere = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"id_filiere", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.id_filiere = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 9;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.annee_entree = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"annee_entree", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.annee_entree = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 10;

							row2.ville_origine = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 11;

							row2.situation_matrimoniale = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 12;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.nb_enfants_a_charge = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"nb_enfants_a_charge", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.nb_enfants_a_charge = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 13;

							row2.estime_de_soi = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 14;

							row2.trait_personnalite = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 15;

							row2.mention_baccalaureat = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 16;

							row2.serie_terminale = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 17;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row2.decrochage_anterieur = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"decrochage_anterieur", "row2", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row2.decrochage_anterieur = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 18;

							row2.frequence_etude_lecons = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 19;

							row2.interet_lecture_ecriture = fid_tFileInputDelimited_2
									.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 20;

							row2.adresse_email = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 21;

							row2.telephone = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

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

								etudiant_Out = null;

// # Output table : 'etudiant_Out'
								etudiant_Out_tmp.id_etudiant = row2.id_etudiant;
								etudiant_Out_tmp.matricule = row2.matricule;
								etudiant_Out_tmp.email_etudiant = row2.email_etudiant;
								etudiant_Out_tmp.nom = row2.nom;
								etudiant_Out_tmp.prenom = row2.prenom;
								etudiant_Out_tmp.genre = row2.genre;
								etudiant_Out_tmp.date_naissance = row2.date_naissance;
								etudiant_Out_tmp.niveau_socio_economique = row2.niveau_socio_economique;
								etudiant_Out_tmp.id_filiere = row2.id_filiere;
								etudiant_Out_tmp.annee_entree = row2.annee_entree;
								etudiant_Out_tmp.ville_origine = row2.ville_origine;
								etudiant_Out_tmp.situation_matrimoniale = row2.situation_matrimoniale;
								etudiant_Out_tmp.nb_enfants_a_charge = row2.nb_enfants_a_charge;
								etudiant_Out_tmp.estime_de_soi = row2.estime_de_soi;
								etudiant_Out_tmp.trait_personnalite = row2.trait_personnalite;
								etudiant_Out_tmp.mention_baccalaureat = row2.mention_baccalaureat;
								etudiant_Out_tmp.serie_terminale = row2.serie_terminale;
								etudiant_Out_tmp.decrochage_anterieur = row2.decrochage_anterieur;
								etudiant_Out_tmp.frequence_etude_lecons = row2.frequence_etude_lecons;
								etudiant_Out_tmp.interet_lecture_ecriture = row2.interet_lecture_ecriture;
								etudiant_Out_tmp.adresse_email = row2.adresse_email;
								etudiant_Out_tmp.telephone = row2.telephone;
								etudiant_Out = etudiant_Out_tmp;
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
// Start of branch "etudiant_Out"
							if (etudiant_Out != null) {

								/**
								 * [tAdvancedHash_etudiant_Out main ] start
								 */

								currentComponent = "tAdvancedHash_etudiant_Out";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "etudiant_Out"

									);
								}

								etudiant_OutStruct etudiant_Out_HashRow = new etudiant_OutStruct();

								etudiant_Out_HashRow.id_etudiant = etudiant_Out.id_etudiant;

								etudiant_Out_HashRow.matricule = etudiant_Out.matricule;

								etudiant_Out_HashRow.email_etudiant = etudiant_Out.email_etudiant;

								etudiant_Out_HashRow.nom = etudiant_Out.nom;

								etudiant_Out_HashRow.prenom = etudiant_Out.prenom;

								etudiant_Out_HashRow.genre = etudiant_Out.genre;

								etudiant_Out_HashRow.date_naissance = etudiant_Out.date_naissance;

								etudiant_Out_HashRow.niveau_socio_economique = etudiant_Out.niveau_socio_economique;

								etudiant_Out_HashRow.id_filiere = etudiant_Out.id_filiere;

								etudiant_Out_HashRow.annee_entree = etudiant_Out.annee_entree;

								etudiant_Out_HashRow.ville_origine = etudiant_Out.ville_origine;

								etudiant_Out_HashRow.situation_matrimoniale = etudiant_Out.situation_matrimoniale;

								etudiant_Out_HashRow.nb_enfants_a_charge = etudiant_Out.nb_enfants_a_charge;

								etudiant_Out_HashRow.estime_de_soi = etudiant_Out.estime_de_soi;

								etudiant_Out_HashRow.trait_personnalite = etudiant_Out.trait_personnalite;

								etudiant_Out_HashRow.mention_baccalaureat = etudiant_Out.mention_baccalaureat;

								etudiant_Out_HashRow.serie_terminale = etudiant_Out.serie_terminale;

								etudiant_Out_HashRow.decrochage_anterieur = etudiant_Out.decrochage_anterieur;

								etudiant_Out_HashRow.frequence_etude_lecons = etudiant_Out.frequence_etude_lecons;

								etudiant_Out_HashRow.interet_lecture_ecriture = etudiant_Out.interet_lecture_ecriture;

								etudiant_Out_HashRow.adresse_email = etudiant_Out.adresse_email;

								etudiant_Out_HashRow.telephone = etudiant_Out.telephone;

								tHash_Lookup_etudiant_Out.put(etudiant_Out_HashRow);

								tos_count_tAdvancedHash_etudiant_Out++;

								/**
								 * [tAdvancedHash_etudiant_Out main ] stop
								 */

								/**
								 * [tAdvancedHash_etudiant_Out process_data_begin ] start
								 */

								currentComponent = "tAdvancedHash_etudiant_Out";

								/**
								 * [tAdvancedHash_etudiant_Out process_data_begin ] stop
								 */

								/**
								 * [tAdvancedHash_etudiant_Out process_data_end ] start
								 */

								currentComponent = "tAdvancedHash_etudiant_Out";

								/**
								 * [tAdvancedHash_etudiant_Out process_data_end ] stop
								 */

							} // End of branch "etudiant_Out"

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
					if (!((Object) ("C:/home/claude/education_bi_v2/dim_etudiant.csv") instanceof java.io.InputStream)) {
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
				 * [tAdvancedHash_etudiant_Out end ] start
				 */

				currentComponent = "tAdvancedHash_etudiant_Out";

				tHash_Lookup_etudiant_Out.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "etudiant_Out");
				}

				ok_Hash.put("tAdvancedHash_etudiant_Out", true);
				end_Hash.put("tAdvancedHash_etudiant_Out", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_etudiant_Out end ] stop
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
				 * [tAdvancedHash_etudiant_Out finally ] start
				 */

				currentComponent = "tAdvancedHash_etudiant_Out";

				/**
				 * [tAdvancedHash_etudiant_Out finally ] stop
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

	public static class filiere_OutStruct
			implements routines.system.IPersistableComparableLookupRow<filiere_OutStruct> {
		final static byte[] commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public int id_filiere;

		public int getId_filiere() {
			return this.id_filiere;
		}

		public String code_filiere;

		public String getCode_filiere() {
			return this.code_filiere;
		}

		public String nom_filiere;

		public String getNom_filiere() {
			return this.nom_filiere;
		}

		public String domaine;

		public String getDomaine() {
			return this.domaine;
		}

		public Integer duree_annees;

		public Integer getDuree_annees() {
			return this.duree_annees;
		}

		public String niveau_diplome;

		public String getNiveau_diplome() {
			return this.niveau_diplome;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + (int) this.id_filiere;

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
			final filiere_OutStruct other = (filiere_OutStruct) obj;

			if (this.id_filiere != other.id_filiere)
				return false;

			return true;
		}

		public void copyDataTo(filiere_OutStruct other) {

			other.id_filiere = this.id_filiere;
			other.code_filiere = this.code_filiere;
			other.nom_filiere = this.nom_filiere;
			other.domaine = this.domaine;
			other.duree_annees = this.duree_annees;
			other.niveau_diplome = this.niveau_diplome;

		}

		public void copyKeysDataTo(filiere_OutStruct other) {

			other.id_filiere = this.id_filiere;

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

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

				try {

					int length = 0;

					this.id_filiere = dis.readInt();

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

				try {

					int length = 0;

					this.id_filiere = dis.readInt();

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.id_filiere);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.id_filiere);

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

				this.code_filiere = readString(dis, ois);

				this.nom_filiere = readString(dis, ois);

				this.domaine = readString(dis, ois);

				this.duree_annees = readInteger(dis, ois);

				this.niveau_diplome = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.code_filiere = readString(dis, objectIn);

				this.nom_filiere = readString(dis, objectIn);

				this.domaine = readString(dis, objectIn);

				this.duree_annees = readInteger(dis, objectIn);

				this.niveau_diplome = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.code_filiere, dos, oos);

				writeString(this.nom_filiere, dos, oos);

				writeString(this.domaine, dos, oos);

				writeInteger(this.duree_annees, dos, oos);

				writeString(this.niveau_diplome, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.code_filiere, dos, objectOut);

				writeString(this.nom_filiere, dos, objectOut);

				writeString(this.domaine, dos, objectOut);

				writeInteger(this.duree_annees, dos, objectOut);

				writeString(this.niveau_diplome, dos, objectOut);

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
			sb.append("id_filiere=" + String.valueOf(id_filiere));
			sb.append(",code_filiere=" + code_filiere);
			sb.append(",nom_filiere=" + nom_filiere);
			sb.append(",domaine=" + domaine);
			sb.append(",duree_annees=" + String.valueOf(duree_annees));
			sb.append(",niveau_diplome=" + niveau_diplome);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(filiere_OutStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.id_filiere, other.id_filiere);
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
		final static byte[] commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons = new byte[0];
		static byte[] commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[0];

		public int id_filiere;

		public int getId_filiere() {
			return this.id_filiere;
		}

		public String code_filiere;

		public String getCode_filiere() {
			return this.code_filiere;
		}

		public String nom_filiere;

		public String getNom_filiere() {
			return this.nom_filiere;
		}

		public String domaine;

		public String getDomaine() {
			return this.domaine;
		}

		public Integer duree_annees;

		public Integer getDuree_annees() {
			return this.duree_annees;
		}

		public String niveau_diplome;

		public String getNiveau_diplome() {
			return this.niveau_diplome;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length == 0) {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length, utf8Charset);
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
				if (length > commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length) {
					if (length < 1024 && commonByteArray_PROJET_BI_Job_03_Faits_Abandons.length == 0) {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[1024];
					} else {
						commonByteArray_PROJET_BI_Job_03_Faits_Abandons = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length);
				strReturn = new String(commonByteArray_PROJET_BI_Job_03_Faits_Abandons, 0, length, utf8Charset);
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

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

				try {

					int length = 0;

					this.id_filiere = dis.readInt();

					this.code_filiere = readString(dis);

					this.nom_filiere = readString(dis);

					this.domaine = readString(dis);

					this.duree_annees = readInteger(dis);

					this.niveau_diplome = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_PROJET_BI_Job_03_Faits_Abandons) {

				try {

					int length = 0;

					this.id_filiere = dis.readInt();

					this.code_filiere = readString(dis);

					this.nom_filiere = readString(dis);

					this.domaine = readString(dis);

					this.duree_annees = readInteger(dis);

					this.niveau_diplome = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.id_filiere);

				// String

				writeString(this.code_filiere, dos);

				// String

				writeString(this.nom_filiere, dos);

				// String

				writeString(this.domaine, dos);

				// Integer

				writeInteger(this.duree_annees, dos);

				// String

				writeString(this.niveau_diplome, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.id_filiere);

				// String

				writeString(this.code_filiere, dos);

				// String

				writeString(this.nom_filiere, dos);

				// String

				writeString(this.domaine, dos);

				// Integer

				writeInteger(this.duree_annees, dos);

				// String

				writeString(this.niveau_diplome, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("id_filiere=" + String.valueOf(id_filiere));
			sb.append(",code_filiere=" + code_filiere);
			sb.append(",nom_filiere=" + nom_filiere);
			sb.append(",domaine=" + domaine);
			sb.append(",duree_annees=" + String.valueOf(duree_annees));
			sb.append(",niveau_diplome=" + niveau_diplome);
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
				filiere_OutStruct filiere_Out = new filiere_OutStruct();

				/**
				 * [tAdvancedHash_filiere_Out begin ] start
				 */

				ok_Hash.put("tAdvancedHash_filiere_Out", false);
				start_Hash.put("tAdvancedHash_filiere_Out", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_filiere_Out";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "filiere_Out");
				}

				int tos_count_tAdvancedHash_filiere_Out = 0;

				// connection name:filiere_Out
				// source node:tMap_3 - inputs:(row3) outputs:(filiere_Out,filiere_Out) | target
				// node:tAdvancedHash_filiere_Out - inputs:(filiere_Out) outputs:()
				// linked node: tMap_4 - inputs:(abandon_Out,etudiant_Out,filiere_Out)
				// outputs:(abandon_Output)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_filiere_Out = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<filiere_OutStruct> tHash_Lookup_filiere_Out = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<filiere_OutStruct>getLookup(matchingModeEnum_filiere_Out);

				globalMap.put("tHash_Lookup_filiere_Out", tHash_Lookup_filiere_Out);

				/**
				 * [tAdvancedHash_filiere_Out begin ] stop
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
				filiere_OutStruct filiere_Out_tmp = new filiere_OutStruct();
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

					Object filename_tFileInputDelimited_3 = "C:/home/claude/education_bi_v2/dim_filiere.csv";
					if (filename_tFileInputDelimited_3 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_3 = 0, random_value_tFileInputDelimited_3 = -1;
						if (footer_value_tFileInputDelimited_3 > 0 || random_value_tFileInputDelimited_3 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_3 = new org.talend.fileprocess.FileInputDelimited(
								"C:/home/claude/education_bi_v2/dim_filiere.csv", "UTF-8", ",", "\n", true, 1, 0,
								limit_tFileInputDelimited_3, -1, false);
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

									row3.id_filiere = ParserUtils.parseTo_int(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
											ex_tFileInputDelimited_3.getMessage());
									rowstate_tFileInputDelimited_3.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"id_filiere", "row3", temp, ex_tFileInputDelimited_3),
											ex_tFileInputDelimited_3));
								}

							} else {

								rowstate_tFileInputDelimited_3.setException(new RuntimeException(
										"Value is empty for column : 'id_filiere' in 'row3' connection, value is invalid or this column should be nullable or have a default value."));

							}

							columnIndexWithD_tFileInputDelimited_3 = 1;

							row3.code_filiere = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 2;

							row3.nom_filiere = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 3;

							row3.domaine = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 4;

							temp = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);
							if (temp.length() > 0) {

								try {

									row3.duree_annees = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_3) {
									globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE",
											ex_tFileInputDelimited_3.getMessage());
									rowstate_tFileInputDelimited_3.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"duree_annees", "row3", temp, ex_tFileInputDelimited_3),
											ex_tFileInputDelimited_3));
								}

							} else {

								row3.duree_annees = null;

							}

							columnIndexWithD_tFileInputDelimited_3 = 5;

							row3.niveau_diplome = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

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

								filiere_Out = null;

// # Output table : 'filiere_Out'
								filiere_Out_tmp.id_filiere = row3.id_filiere;
								filiere_Out_tmp.code_filiere = row3.code_filiere;
								filiere_Out_tmp.nom_filiere = row3.nom_filiere;
								filiere_Out_tmp.domaine = row3.domaine;
								filiere_Out_tmp.duree_annees = row3.duree_annees;
								filiere_Out_tmp.niveau_diplome = row3.niveau_diplome;
								filiere_Out = filiere_Out_tmp;
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
// Start of branch "filiere_Out"
							if (filiere_Out != null) {

								/**
								 * [tAdvancedHash_filiere_Out main ] start
								 */

								currentComponent = "tAdvancedHash_filiere_Out";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "filiere_Out"

									);
								}

								filiere_OutStruct filiere_Out_HashRow = new filiere_OutStruct();

								filiere_Out_HashRow.id_filiere = filiere_Out.id_filiere;

								filiere_Out_HashRow.code_filiere = filiere_Out.code_filiere;

								filiere_Out_HashRow.nom_filiere = filiere_Out.nom_filiere;

								filiere_Out_HashRow.domaine = filiere_Out.domaine;

								filiere_Out_HashRow.duree_annees = filiere_Out.duree_annees;

								filiere_Out_HashRow.niveau_diplome = filiere_Out.niveau_diplome;

								tHash_Lookup_filiere_Out.put(filiere_Out_HashRow);

								tos_count_tAdvancedHash_filiere_Out++;

								/**
								 * [tAdvancedHash_filiere_Out main ] stop
								 */

								/**
								 * [tAdvancedHash_filiere_Out process_data_begin ] start
								 */

								currentComponent = "tAdvancedHash_filiere_Out";

								/**
								 * [tAdvancedHash_filiere_Out process_data_begin ] stop
								 */

								/**
								 * [tAdvancedHash_filiere_Out process_data_end ] start
								 */

								currentComponent = "tAdvancedHash_filiere_Out";

								/**
								 * [tAdvancedHash_filiere_Out process_data_end ] stop
								 */

							} // End of branch "filiere_Out"

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
					if (!((Object) ("C:/home/claude/education_bi_v2/dim_filiere.csv") instanceof java.io.InputStream)) {
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
				 * [tAdvancedHash_filiere_Out end ] start
				 */

				currentComponent = "tAdvancedHash_filiere_Out";

				tHash_Lookup_filiere_Out.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "filiere_Out");
				}

				ok_Hash.put("tAdvancedHash_filiere_Out", true);
				end_Hash.put("tAdvancedHash_filiere_Out", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_filiere_Out end ] stop
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
				 * [tAdvancedHash_filiere_Out finally ] start
				 */

				currentComponent = "tAdvancedHash_filiere_Out";

				/**
				 * [tAdvancedHash_filiere_Out finally ] stop
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
		final Job_03_Faits_Abandons Job_03_Faits_AbandonsClass = new Job_03_Faits_Abandons();

		int exitCode = Job_03_Faits_AbandonsClass.runJobInTOS(args);

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
			java.io.InputStream inContext = Job_03_Faits_Abandons.class.getClassLoader()
					.getResourceAsStream("projet_bi/job_03_faits_abandons_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Job_03_Faits_Abandons.class.getClassLoader()
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
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println(
					(endUsedMemory - startUsedMemory) + " bytes memory increase when running : Job_03_Faits_Abandons");
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
 * 196723 characters generated by Talend Open Studio for Data Integration on the
 * 10 mai 2026 à 23:16:12 WAT
 ************************************************************************************************/