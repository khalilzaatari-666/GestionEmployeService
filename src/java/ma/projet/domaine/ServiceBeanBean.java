/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

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
@ManagedBean(name = "serviceBean")
public class ServiceBeanBean {

    private Employe employe;
    private ServiceBean service;
    private ServiceBeanService serviceService;
    private List<ServiceBean> services;
    private List<Employe> employes;
    private EmployeService employeService;
    private static ChartModel barModel;

    public ServiceBeanBean() {
        service = new ServiceBean();
        serviceService = new ServiceBeanService();
        employe = new Employe();
        employeService = new EmployeService();
    }

    public List<ServiceBean> getServices() {
        if (services == null) {
            services = serviceService.getAll();
        }
        return services;
    }

    public void setServices(List<ServiceBean> services) {
        this.services = services;
    }

    public ServiceBean getService() {
        return service;
    }

    public void setService(ServiceBean service) {
        this.service = service;
    }

    public String onCreateAction() {
        serviceService.create(service);
        service  = new ServiceBean();
        return null;
    }

    public void onDeleteAction() {
        serviceService.delete(service);

    }

    public void onEdit(RowEditEvent event) {
        service = (ServiceBean) event.getObject();
        serviceService.update(service);
    }

    public void load() {
        System.out.println(service.getNom());
        service = serviceService.getById(service.getId());
        getServices();
    }

    public void onCancel(RowEditEvent event) {
    }

    public void onEditm(RowEditEvent event) {
        employe = (Employe) event.getObject();
        ServiceBean service = serviceService.getById(this.employe.getService().getId());
        employe.setService(service);
        employe.getService().setNom(service.getNom());
        employeService.update(employe);
    }

    public String onDeleteActionm() {

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

    public void onCancelm(RowEditEvent event) {
    }

    public ChartModel getBarModel() {
        return barModel;
    }

    public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries services = new ChartSeries();
        services.setLabel("Services");
        model.setAnimate(true);
        for (ServiceBean s : serviceService.getAll()) {
            services.set(s.getNom(), serviceLoad().size());
        }
        model.addSeries(services);
        
                
        return model;
    }
}
