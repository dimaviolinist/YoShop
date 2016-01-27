package by.itacademy.training.yoshop.entity;

import java.util.Date;

public class BlackListRecord {

	private Date	banStart;
	private Date	banEnd;
	private String	reason;

	public BlackListRecord() {

	}

	public Date getBanStart() {
		return banStart;
	}

	public void setBanStart(Date banStart) {
		this.banStart = banStart;
	}

	public Date getBanEnd() {
		return banEnd;
	}

	public void setBanEnd(Date banEnd) {
		this.banEnd = banEnd;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((banEnd == null) ? 0 : banEnd.hashCode());
		result = prime * result + ((banStart == null) ? 0 : banStart.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlackListRecord other = (BlackListRecord) obj;
		if (banEnd == null) {
			if (other.banEnd != null)
				return false;
		} else if (!banEnd.equals(other.banEnd))
			return false;
		if (banStart == null) {
			if (other.banStart != null)
				return false;
		} else if (!banStart.equals(other.banStart))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BlackListRecord [banStart=");
		builder.append(banStart);
		builder.append(", banEnd=");
		builder.append(banEnd);
		builder.append(", reason=");
		builder.append(reason);
		builder.append("]");
		return builder.toString();
	}

}
