/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import ma.projet.beans.Employe;
import ma.projet.beans.ServiceBean;
import ma.projet.service.EmployeService;
import ma.projet.service.ServiceBeanService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author LACHGAR
 */
@ManagedBean(name = "employeBean")
public class EmployeBean {

    private Employe employe;

    private ServiceBean service;
    private List<Employe> employes;
    private List<Employe> employesBetweenDates;
    private EmployeService employeService;
    private ServiceBeanService serviceBeanService;
    private static ChartModel barModel;
    private Date date1;
    private Date date2;

    public EmployeBean() {
        employe = new Employe();
        employeService = new EmployeService();
        serviceBeanService = new ServiceBeanService();

    }

    public List<Employe> getEmployes() {
        if (employes == null) {
            employes = employeService.getAll();
        }
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public EmployeService getEmployeService() {
        return employeService;
    }

    public void setEmployeService(EmployeService employeService) {
        this.employeService = employeService;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public String onCreateAction() {
        employeService.create(employe);
        employe = new Employe();
        return null;
    }

    public String onDeleteAction() {

        employeService.delete(employeService.getById(employe.getId()));
        return null;
    }

    public List<Employe> serviceLoad() {
        for (Employe m : employeService.getAll()) {
            if (m.getService().equals(service)) {
                employes.add(m);
            }
        }
        return employes;

    }

    public void load() {
        System.out.println(service.getNom());
        service = serviceBeanService.getById(service.getId());
        getEmployes();
    }

    public void onEdit(RowEditEvent event) {
        employe = (Employe) event.getObject();
        ServiceBean service = serviceBeanService.getById(this.employe.getService().getId());
        employe.setService(service);
        employe.getService().setNom(service.getNom());
        employeService.update(employe);
    }

    public void onCancel(RowEditEvent event) {
    }

    public ChartModel getBarModel() {
        return barModel;
    }

    public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries employes = new ChartSeries();
        employes.setLabel("employes");
        model.setAnimate(true);
        for (Object[] m : employeService.nbemploye()) {
            employes.set(m[1], Integer.parseInt(m[0].toString()));
        }
        model.addSeries(employes);

        return model;
    }

    public List<Employe> machineLoad() {
        employesBetweenDates = employeService.getbydates(date1, date2);
        return null;

    }

    public ServiceBean getService() {
        return service;
    }

    public void setService(ServiceBean service) {
        this.service = service;
    }

    public List<Employe> getEmployesBetweenDates() {
        return employesBetweenDates;
    }

    public void setEmployesBetweenDates(List<Employe> employesBetweenDates) {
        this.employesBetweenDates = employesBetweenDates;
    }

}
