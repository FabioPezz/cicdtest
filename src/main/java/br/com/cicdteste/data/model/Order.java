package br.com.cicdteste.data.model;

import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TB_ORDER")
public class Order {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id", nullable=false)
	private Long id;
	
	@Column(name="operator_id", nullable=false, length=5)
	private Long operatorId;
	
	public void setOperatorId(Long operatorId) {
		this.operatorId = operatorId;
	}

	@ManyToMany
    private List<Assistance> assists;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "start_order_location_id", foreignKey = @ForeignKey(name ="FK_start_order_id"))
    private OrderLocation startOrderLocation;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "end_order_location_id" , foreignKey = @ForeignKey(name ="FK_end_order_id"))
    private OrderLocation endOrderLocation;

    
    public boolean hasMinAssists() {
    	return assists.size() > 0;
    }
    
    public boolean exceedsMaxAssists() {
    	return assists.size() > 15;
    }

	public List<Assistance> getAssists() {
		return assists;
	}

	public void setAssists(List<Assistance> assists) {
		this.assists = assists;
	}
}