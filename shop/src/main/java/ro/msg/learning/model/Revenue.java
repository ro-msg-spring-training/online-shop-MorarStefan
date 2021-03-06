package ro.msg.learning.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Revenue")
@Table(name = "revenue", schema = "shop")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Revenue {
	
	@Id
	@Column(name = "id", nullable = false, unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "sum", nullable = false)
	private BigDecimal sum;
}
