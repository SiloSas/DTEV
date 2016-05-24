package util

import javax.inject.Inject

import play.api.http.HttpFilters
import play.filters.gzip.GzipFilter

class Gzip @Inject()(gzipFilter: GzipFilter) extends HttpFilters {
  def filters = Seq(gzipFilter)
}
