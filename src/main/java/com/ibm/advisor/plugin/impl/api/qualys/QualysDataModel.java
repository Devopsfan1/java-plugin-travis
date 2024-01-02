/*
 * IBM Confidential
 * PID 5900-B4I
 * © Copyright IBM Corp. 2023
 *
 */
package com.ibm.advisor.plugin.impl.api.qualys;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class QualysDataModel {
	private String scan_report_template_title;
    private String result_date;
    private String company;
    private String add1;
    private Object add2;
    private String city;
    private Object state;
    private String country;
    private String zip;
    private String name;
    private String username;
    private String role;
    private String launch_date;
    private String active_hosts;
    private String total_hosts;
    private String type;
    private String status;
    private String reference;
    private String scanner_appliance;
    private String duration;
    private String scan_title;
    private String asset_groups;
    private String ips;
    private String excluded_ips;
    private String option_profile;
    private String ip;
    private String dns;
    private Object netbios;
    private Object os;
    private String ip_status;
    private int qid;
    private String title;
    private String severity;
    private String port;
    private String protocol;
    private String fqdn;
    private String ssl;
    private Object cve_id;
    private Object vendor_reference;
    private Object bugtraq_id;
    private String threat;
    private String impact;
    private String solution;
    private Object exploitability;
    private Object associated_malware;
    private String results;
    private String pci_vuln;
    private Object instance;
    private String category;
    private String target_distribution_across_scanner_appliances;
}
